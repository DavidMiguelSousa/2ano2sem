package eapli.base.usermanagement.application;

import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.services.JobApplicationManagementService;
import eapli.base.services.JobOpeningManagementService;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

@UseCaseController
public class DisplayJobApplicationDataController {

    AuthorizationService authz = AuthzRegistry.authorizationService();
    JobApplicationManagementService jobApplicationManagementService = new JobApplicationManagementService(PersistenceContext.repositories().jobApplications());
    JobOpeningManagementService jobOpeningManagementService = new JobOpeningManagementService(PersistenceContext.repositories().jobOpenings());

    public Iterable<JobOpening> jobOpenings() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER);

        return jobOpeningManagementService.findAllAvailable();
    }

    public Iterable<JobApplication> jobApplicationsByJobOpening(JobOpening jobOpening) {
        return jobApplicationManagementService.jobApplicationsOf(jobOpening);
    }

    public void displayAllData(JobApplication jobApplication) {
        jobApplicationManagementService.displayApplicationData(jobApplication);
    }
}