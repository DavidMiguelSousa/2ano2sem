package eapli.base.counter.application;

import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.clientusermanagement.repositories.JobApplicationRepository;
import eapli.base.clientusermanagement.repositories.JobOpeningRepository;
import eapli.base.counter.domain.WordAggregator;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.services.JobApplicationManagementService;
import eapli.base.services.JobOpeningManagementService;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.base.counter.domain.TopWords;
import eapli.base.counter.services.CountTopWordsService;

import java.io.File;
import java.util.List;

public class CountTopWordsController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final JobOpeningRepository jobOpeningRepo = PersistenceContext.repositories().jobOpenings();
    private final JobApplicationRepository jobApplicationRepo = PersistenceContext.repositories().jobApplications();

    private final JobOpeningManagementService jobOpeningManagementService = new JobOpeningManagementService(jobOpeningRepo);
    private final JobApplicationManagementService applicationManagementService = new JobApplicationManagementService(jobApplicationRepo);
    private final CountTopWordsService countTopWordsService = new CountTopWordsService();

    public Iterable<JobOpening> jobOpenings() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER);

        return jobOpeningManagementService.findAllAvailable();
    }

    public List<File> files(JobApplication jobApplication, JobOpening jobOpening) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER);

        return applicationManagementService.files(jobApplication, jobOpening);
    }

    public Iterable<JobApplication> jobApplicationsOf(JobOpening jobOpening) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER);

        return applicationManagementService.jobApplicationsOf(jobOpening);
    }

    public TopWords countTopWords(List<File> files) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER);

        return countTopWordsService.countTopWords(files);
    }

    public WordAggregator filesWhereWordIsPresent(String word) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER);

        return countTopWordsService.filesWhereWordIsPresent(word);
    }
}
