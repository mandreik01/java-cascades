package pl.gov.coi.cascades.contract.configuration;

import com.google.common.base.Optional;
import pl.gov.coi.cascades.contract.domain.DatabaseType;
import pl.gov.coi.cascades.contract.domain.TemplateId;
import lombok.Getter;

import javax.annotation.Nullable;

/**
 * This class represents a database driver configuration
 */
public class Driver {

    @Getter
    private final DatabaseType type;
    private final TemplateId templateId;

    /**
     * Required argument constructor.
     *
     * @param type       Given type of driver.
     * @param templateId Given id of template of driver.
     */
    public Driver(DatabaseType type,
                  @Nullable TemplateId templateId) {
        this.type = type;
        this.templateId = templateId;
    }

    /**
     * Gets an optional id of template.
     *
     * @return Optional id of template.
     */
    public Optional<TemplateId> getTemplateId() {
        return Optional.fromNullable(templateId);
    }

}