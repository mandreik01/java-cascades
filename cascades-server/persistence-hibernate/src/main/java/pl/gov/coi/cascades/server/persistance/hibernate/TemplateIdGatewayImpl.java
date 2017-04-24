package pl.gov.coi.cascades.server.persistance.hibernate;

import com.google.common.annotations.VisibleForTesting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.gov.coi.cascades.server.domain.TemplateIdGateway;
import pl.gov.coi.cascades.server.persistance.hibernate.entity.TemplateId;
import pl.gov.coi.cascades.server.persistance.hibernate.mapper.TemplateIdMapper;
import pl.wavesoftware.eid.exceptions.Eid;

import javax.annotation.Nullable;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

/**
 * @author <a href="agnieszka.celuch@coi.gov.pl">Agnieszka Celuch</a>
 * @since 30.03.17.
 */
public class TemplateIdGatewayImpl implements TemplateIdGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateIdGatewayImpl.class);
    private static final TemplateIdMapper DEFAULT_TEMPLATE_ID_MAPPER = new TemplateIdMapper();
    private static final String TEMPLATE_ID_FIELD = "templateIdAsLong";
    private static final int RADIX_36 = 36;
    private EntityManager entityManager;
    private final TemplateIdMapper templateIdMapper;

    public TemplateIdGatewayImpl() {
        this(DEFAULT_TEMPLATE_ID_MAPPER);
    }

    @VisibleForTesting
    TemplateIdGatewayImpl(TemplateIdMapper templateIdMapper) {
        this.templateIdMapper = templateIdMapper;
    }

    @PersistenceContext
    void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<pl.gov.coi.cascades.contract.domain.TemplateId> find(@Nullable String templateId) {
        try {
            Long templateIdAsLong = templateId != null
                ? Long.parseLong(templateId, RADIX_36)
                : null;
            TypedQuery<TemplateId> query =
                entityManager.createQuery(
                    "SELECT template FROM TemplateId template " +
                        "WHERE template.id = :templateIdAsLong",
                    TemplateId.class
                )
                .setParameter(TEMPLATE_ID_FIELD, templateIdAsLong)
                .setMaxResults(1);

            return Optional.of(templateIdMapper.fromHibernateEntity(query.getSingleResult()));
        } catch (NoResultException e) {
            LOGGER.error(new Eid("20170330:092228")
                .makeLogMessage(
                    "Given id of template: %s hasn't been found: %s.",
                    templateId,
                    e
                )
            );
            return Optional.empty();
        }
    }

    @Override
    public Optional<pl.gov.coi.cascades.contract.domain.TemplateId> getDefaultTemplateId() {
        try {
            TypedQuery<TemplateId> query =
                entityManager.createQuery(
                    "SELECT template FROM TemplateId template " +
                        "WHERE template.isDefault = true",
                    TemplateId.class
                )
                .setMaxResults(1);

            return Optional.of(templateIdMapper.fromHibernateEntity(query.getSingleResult()));
        } catch (NoResultException e) {
            LOGGER.error(new Eid("20170406:092655")
                .makeLogMessage(
                    "No default template id has been found",
                    e
                )
            );
            return Optional.empty();
        }
    }

}