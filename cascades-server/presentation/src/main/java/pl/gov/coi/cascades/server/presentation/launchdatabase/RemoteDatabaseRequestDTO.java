package pl.gov.coi.cascades.server.presentation.launchdatabase;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.gov.coi.cascades.contract.domain.TemplateId;
import pl.gov.coi.cascades.contract.service.RemoteDatabaseRequest;

import javax.annotation.Nullable;

/**
 * @author <a href="agnieszka.celuch@coi.gov.pl">Agnieszka Celuch</a>
 * @since 03.03.17.
 */
final class RemoteDatabaseRequestDTO extends RemoteDatabaseRequest {
    /**
     * {@inheritDoc}
     */
    public RemoteDatabaseRequestDTO(@JsonProperty("type") String type,
                                    @JsonProperty("templateId") @Nullable String templateId,
                                    @JsonProperty("instanceName") @Nullable String instanceName) {
        super(type, createTemplateId(templateId), instanceName);
    }

    @Nullable
    private static TemplateId createTemplateId(@Nullable String templateId) {
        return templateId == null
            ? null
            : new TemplateId(templateId);
    }
}
