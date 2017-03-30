package pl.gov.coi.cascades.server.presentation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.gov.coi.cascades.server.OsgiBeanLocator;
import pl.gov.coi.cascades.server.domain.launchdatabase.DatabaseNameGeneratorService;
import pl.gov.coi.cascades.server.domain.DatabaseTypeClassNameService;
import pl.gov.coi.cascades.server.domain.OsgiDatabaseTypeClassNameService;
import pl.gov.coi.cascades.server.domain.launchdatabase.UsernameAndPasswordCredentialsGeneratorService;

/**
 * @author <a href="agnieszka.celuch@coi.gov.pl">Agnieszka Celuch</a>
 * @since 02.03.17.
 */
@Configuration
public class PresentationConfiguration {

    @Bean
    DatabaseNameGeneratorService produceDatabaseIdGeneratorService() {
        return new DatabaseNameGeneratorService();
    }

    @Bean
    UsernameAndPasswordCredentialsGeneratorService produceCredentials() {
        return new UsernameAndPasswordCredentialsGeneratorService();
    }

    @Bean
    DatabaseTypeClassNameService produceDatabaseTypeClassName(OsgiBeanLocator locator) {
        return new OsgiDatabaseTypeClassNameService(locator);
    }

}
