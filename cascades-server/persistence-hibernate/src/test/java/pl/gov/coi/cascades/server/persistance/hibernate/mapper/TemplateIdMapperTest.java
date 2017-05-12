package pl.gov.coi.cascades.server.persistance.hibernate.mapper;

import org.junit.Test;
import pl.gov.coi.cascades.contract.domain.TemplateIdStatus;
import pl.gov.coi.cascades.server.persistance.hibernate.entity.TemplateId;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="agnieszka.celuch@coi.gov.pl">Agnieszka Celuch</a>
 * @since 30.03.17.
 */
public class TemplateIdMapperTest {

    private static final int BASE_36 = 36;

    @Test
    public void testToHibernateEntity() throws Exception {
        // given
        TemplateIdMapper templateIdMapper = new TemplateIdMapper();
        String id = "673735756";
        String serverId = "fre5345";
        String version = "0.0.1";
        pl.gov.coi.cascades.contract.domain.TemplateId templateId = new pl.gov.coi.cascades.contract.domain.TemplateId(
            id,
            TemplateIdStatus.CREATED,
            true,
            serverId,
            version
        );

        // when
        TemplateId actual = templateIdMapper.toHibernateEntity(templateId);

        // then
        assertThat(actual).isNotNull();
        assertThat(actual.getName()).isEqualTo(id);
        assertThat(actual.isDefault()).isTrue();
        assertThat(actual.getServerId()).isEqualTo(serverId);
        assertThat(actual.getStatus().name()).isEqualTo(TemplateIdStatus.CREATED.name());
        assertThat(actual.getVersion()).isEqualTo(version);
    }

    @Test
    public void testFromHibernateEntity() throws Exception {
        // given
        TemplateIdMapper templateIdMapper = new TemplateIdMapper();
        TemplateId templateId = new TemplateId();
        String id = "54363463456";
        String serverId = "fre5345";
        String version = "0.0.1";
        templateId.setName(id);
        templateId.setDefault(false);
        templateId.setServerId(serverId);
        templateId.setVersion(version);
        templateId.setStatus(pl.gov.coi.cascades.server.persistance.hibernate.entity.TemplateIdStatus.DELETED);

        // when
        pl.gov.coi.cascades.contract.domain.TemplateId actual = templateIdMapper.fromHibernateEntity(
            templateId
        );

        // then
        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(id);
        assertThat(actual.isDefault()).isFalse();
        assertThat(actual.getServerId()).isEqualTo(serverId);
        assertThat(actual.getVersion()).isEqualTo(version);
        assertThat(actual.getStatus().name()).isEqualTo(TemplateIdStatus.DELETED.name());
    }

}
