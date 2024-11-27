package eapli.base.infrastructure.bootstrapers;

import eapli.base.candidatemanagement.application.RegisterCandidateController;
import eapli.base.candidatemanagement.domain.Candidate;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class CandidateUsersBootstrapper extends UsersBootstrapperBase implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(CandidateUsersBootstrapper.class);

    private final RegisterCandidateController registerCandidateController = new RegisterCandidateController();

    @Override
    public boolean execute() {
        registerCandidate("candidate@gmail.com", "Candidate1!", "Candidate", "Dummy", BaseRoles.CANDIDATE);
        return true;
    }

    private Candidate registerCandidate(final String email, final String password, final String firstName,
                                final String lastName, final Role role) {
        Candidate candidate = null;
        final Set<Role> roles = new HashSet<>();
        roles.add(role);

        SystemUser user = registerUser(email, password, firstName, lastName, email, roles);
        try {
            candidate = registerCandidateController.registerCandidate(user);
            LOGGER.debug("»»» %s", candidate.identity());
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            Optional<Candidate> optionalCandidate = registerCandidateController.candidateWithUsername(email);
            if (optionalCandidate.isPresent()) candidate = optionalCandidate.get();
        }

        return candidate;
    }
}
