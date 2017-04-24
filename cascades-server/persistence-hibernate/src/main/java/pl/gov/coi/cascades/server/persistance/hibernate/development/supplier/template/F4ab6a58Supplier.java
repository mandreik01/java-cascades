package pl.gov.coi.cascades.server.persistance.hibernate.development.supplier.template;

import pl.gov.coi.cascades.server.persistance.hibernate.development.DevelopmentBean;
import pl.gov.coi.cascades.server.persistance.hibernate.entity.TemplateId;
import pl.gov.coi.cascades.server.persistance.hibernate.entity.TemplateIdStatus;

import java.util.function.Supplier;

/**
 * @author <a href="agnieszka.celuch@coi.gov.pl">Agnieszka Celuch</a>
 * @since 30.03.17.
 */
@DevelopmentBean
public class F4ab6a58Supplier implements Supplier<TemplateId> {

    @Override
    public TemplateId get() {
        TemplateId templateId = new TemplateId();
        templateId.setStatus(TemplateIdStatus.CREATED);
        templateId.setName("postgres_template");
        templateId.setServerId("dgrt45gtyt");
        templateId.setDefault(false);
        return templateId;
    }

}