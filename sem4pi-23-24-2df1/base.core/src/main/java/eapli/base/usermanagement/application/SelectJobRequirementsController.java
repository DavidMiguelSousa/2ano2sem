package eapli.base.usermanagement.application;

import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.clientusermanagement.domain.jobopening.JobRequirements;
import eapli.base.clientusermanagement.repositories.JobApplicationRepository;
import eapli.base.clientusermanagement.repositories.JobOpeningRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.services.JobOpeningManagementService;
import eapli.base.services.JobRequirementsManagementService;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.Optional;

public class SelectJobRequirementsController {
    private final JobOpeningRepository repo = PersistenceContext.repositories().jobOpenings();

    private final JobOpeningManagementService jobOpeningManagementService = new JobOpeningManagementService(repo);
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final JobRequirementsManagementService jobRequirementsService = new JobRequirementsManagementService(PersistenceContext.repositories().jobRequirements());

    public Iterable<JobRequirements> findJobRequirements() {

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER);

        return jobRequirementsService.allJobRequirements();
    }

    public Optional<JobRequirements> randomJobRequirements() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER);

        return Optional.of(jobRequirementsService.allJobRequirements().iterator().next());
    }

    public void assignJobRequirements(JobOpening jobOpening, JobRequirements jobRequirements) {
//        Optional<JobOpening> jobOpeningOpt = jobOpeningManagementService.findJobOpeningById(jobReference);
//        Iterable<JobRequirements> jobRequirementsOpt = jobRequirementsService.findJobRequirements(jobRequirementsReference);
//
//        JobOpening jobOpening = null;
//        if (jobOpeningOpt.isPresent()) jobOpening = jobOpeningOpt.get();
//        assert jobOpening != null;
//        JobRequirements jobRequirements = jobRequirementsOpt.iterator().next();
        jobOpeningManagementService.assignJobRequirements(jobOpening, jobRequirements);
    }


    public JobRequirements findJobRequirementSetByDesignation(Designation jobRequirementSetReference) {
        return jobRequirementsService.findJobRequirements(jobRequirementSetReference).iterator().next();
    }

    public Iterable<JobOpening> pendingJobOpenings() {
        return jobOpeningManagementService.pendingJobOpenings();
    }
}
