package pl.gov.coi.cascades.server.domain.launchdatabase;

import com.google.common.annotations.VisibleForTesting;
import pl.gov.coi.cascades.contract.domain.DatabaseId;
import pl.gov.coi.cascades.server.domain.DatabaseIdMapper;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author <a href="agnieszka.celuch@coi.gov.pl">Agnieszka Celuch</a>
 * @since 03.04.17.
 */
public class DatabaseIdGeneratorService {
    private static final Random DEFUALT_RND = new SecureRandom();
    private static final DatabaseIdMapper DEFAULT_DATABASE_ID_MAPPER = new DatabaseIdMapper();
    private final Random random;
    private final DatabaseIdMapper databaseIdMapper;

    public DatabaseIdGeneratorService() {
        this(
            DEFUALT_RND,
            DEFAULT_DATABASE_ID_MAPPER
        );
    }

    @VisibleForTesting
    DatabaseIdGeneratorService(Random random,
                               DatabaseIdMapper databaseIdMapper) {
        this.random = random;
        this.databaseIdMapper = databaseIdMapper;
    }

    DatabaseId generate() {
        // unsigned long
        long longId = random.nextLong() >>> 1;
        return databaseIdMapper.fromHibernateEntity(longId);
    }
}
