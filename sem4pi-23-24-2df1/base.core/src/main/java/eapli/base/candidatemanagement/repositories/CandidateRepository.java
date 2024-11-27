package eapli.base.candidatemanagement.repositories;

import eapli.base.candidatemanagement.domain.Candidate;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;

import java.util.Optional;

public interface CandidateRepository
        extends DomainRepository<Username, Candidate> {

    @Override
    Optional<Candidate> ofIdentity(Username id);

    Iterable<Candidate> activeCandidates();

    Iterable<Candidate> inactiveCandidates();
}
