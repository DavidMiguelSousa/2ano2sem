package eapli.base.persistence.impl.inmemory;

import eapli.base.candidatemanagement.domain.Candidate;
import eapli.base.candidatemanagement.repositories.CandidateRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Optional;

public class InMemoryCandidateRepository
    extends InMemoryDomainRepository<Candidate, Username>
    implements CandidateRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Optional<Candidate> ofIdentity(Username id) {
        return matchOne(e -> e.user().username().equals(id));
    }

    @Override
    public Iterable<Candidate> activeCandidates() {
        return match(Candidate::isActive);
    }

    @Override
    public Iterable<Candidate> inactiveCandidates() {
        return match(e -> !e.isActive());
    }
}
