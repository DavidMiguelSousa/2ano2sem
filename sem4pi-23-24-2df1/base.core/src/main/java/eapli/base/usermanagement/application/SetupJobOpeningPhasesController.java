package eapli.base.usermanagement.application;

import eapli.base.clientusermanagement.domain.jobopening.Phase;
import eapli.base.clientusermanagement.domain.jobopening.PhaseDetails;
import eapli.base.clientusermanagement.domain.jobopening.Status;
import eapli.base.clientusermanagement.repositories.JobOpeningRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.services.JobOpeningManagementService;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.time.domain.model.DateInterval;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;

import java.util.List;
import java.util.Map;

public class SetupJobOpeningPhasesController {
    private final JobOpeningRepository repo = PersistenceContext.repositories().jobOpenings();

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final JobOpeningManagementService jobOpeningSvc = new JobOpeningManagementService(repo);

    public List<JobOpening> obtainJobOpeningsAvailable() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.CUSTOMER_MANAGER);

        return jobOpeningSvc.findAllAvailable();
    }

    public void setupPhases(JobOpening jobOpening, Map<Phase, PhaseDetails> phases) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.CUSTOMER_MANAGER);

        jobOpeningSvc.setupPhases(jobOpening, phases);
    }
}
