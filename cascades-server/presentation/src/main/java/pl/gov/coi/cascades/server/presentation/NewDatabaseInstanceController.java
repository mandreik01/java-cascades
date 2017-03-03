package pl.gov.coi.cascades.server.presentation;

import pl.gov.coi.cascades.contract.service.CascadesLaunchService;
import pl.gov.coi.cascades.contract.service.RemoteDatabaseRequest;
import pl.gov.coi.cascades.contract.service.RemoteDatabaseSpec;
import pl.gov.coi.cascades.server.domain.LaunchNewDatabaseInstanceRequest;
import pl.gov.coi.cascades.server.domain.LaunchNewDatabaseInstanceRequest.LaunchNewDatabaseInstanceRequestBuilder;
import pl.gov.coi.cascades.server.domain.LaunchNewDatabaseInstanceUseCase;
import pl.gov.coi.cascades.server.domain.User;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class NewDatabaseInstanceController implements CascadesLaunchService {

    private final LaunchNewDatabaseInstanceUseCase launchNewDatabaseInstanceUseCase;
    private final NewDatabaseInstancePresenter newDatabaseInstancePresenter;
    private final UserSession userSession;
    private final OptionalMapper optionalMapper;

    @Inject
    public NewDatabaseInstanceController(UserSession userSession,
                                         LaunchNewDatabaseInstanceUseCase launchNewDatabaseInstanceUseCase,
                                         OptionalMapper optionalMapper,
                                         NewDatabaseInstancePresenter newDatabaseInstancePresenter) {
        this.userSession = userSession;
        this.launchNewDatabaseInstanceUseCase = launchNewDatabaseInstanceUseCase;
        this.newDatabaseInstancePresenter = newDatabaseInstancePresenter;
        this.optionalMapper = optionalMapper;
    }

    /**
     * Method gives future of specification of remote database for given request.
     *
     * @param request Given database creation request.
     * @return a future of remote database specification
     */
    @Override
    public Future<RemoteDatabaseSpec> launchDatabase(RemoteDatabaseRequest request) {
        User user = userSession.getSignedInUser();

        LaunchNewDatabaseInstanceRequestBuilder requestBuilder = LaunchNewDatabaseInstanceRequest.builder()
            .typeClassName(request.getTypeClassName())
            .user(user);

        optionalMapper.toJava8(request.getTemplateId())
            .ifPresent(templateId -> requestBuilder.templateId(templateId.getId()));
        optionalMapper.toJava8(request.getInstanceName())
            .ifPresent(requestBuilder::instanceName);

        LaunchNewDatabaseInstanceRequest launchNewDatabaseInstanceRequest = requestBuilder.build();
        launchNewDatabaseInstanceUseCase.execute(
            launchNewDatabaseInstanceRequest,
            newDatabaseInstancePresenter
        );

        NewDatabaseInstanceViewModel newDatabaseInstanceViewModel = newDatabaseInstancePresenter.createModel();
        return CompletableFuture.completedFuture(newDatabaseInstanceViewModel);
    }

}
