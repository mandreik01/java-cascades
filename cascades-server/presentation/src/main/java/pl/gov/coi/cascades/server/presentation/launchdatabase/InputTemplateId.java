package pl.gov.coi.cascades.server.presentation.launchdatabase;

import pl.gov.coi.cascades.contract.domain.TemplateId;
import pl.gov.coi.cascades.contract.domain.TemplateIdStatus;

/**
 * @author <a href="agnieszka.celuch@coi.gov.pl">Agnieszka Celuch</a>
 * @since 14.04.17.
 */
class InputTemplateId extends TemplateId {

    private static final long serialVersionUID = 42L;

    private static final TemplateIdStatus DEFAULT_STATUS = TemplateIdStatus.CREATED;
    private static final boolean IS_DEFAULT = false;
    private static final String DEFAULT_SERVER_ID = null;

    InputTemplateId(String id) {
        super(id, DEFAULT_STATUS, IS_DEFAULT, DEFAULT_SERVER_ID);
    }

}