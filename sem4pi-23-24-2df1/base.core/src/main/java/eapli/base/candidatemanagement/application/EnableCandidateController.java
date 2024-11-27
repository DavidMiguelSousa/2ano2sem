package eapli.base.candidatemanagement.application;

import eapli.base.candidatemanagement.domain.Candidate;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.services.CandidateManagementService;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

@UseCaseController
public class EnableCandidateController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CandidateManagementService candSvc = new CandidateManagementService(PersistenceContext.repositories().candidates());

    public Iterable<Candidate> inactiveCandidates() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER, BaseRoles.OPERATOR);

        return candSvc.inactiveCandidates();
    }

    public Candidate enableCandidate(final Candidate candidate) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER, BaseRoles.OPERATOR);

        return candSvc.enableCandidate(candidate);
    }
}
