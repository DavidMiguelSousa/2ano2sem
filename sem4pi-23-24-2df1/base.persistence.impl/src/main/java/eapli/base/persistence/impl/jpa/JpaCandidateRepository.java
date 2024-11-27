package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.candidatemanagement.domain.Candidate;
import eapli.base.candidatemanagement.repositories.CandidateRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jakarta.persistence.TypedQuery;

import java.util.Optional;

public class JpaCandidateRepository
        extends JpaAutoTxRepository<Candidate, Username, Username>
        implements CandidateRepository {

    public JpaCandidateRepository(TransactionalContext autoTx) {
        super(autoTx, "username");
    }

    public JpaCandidateRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "username");
    }

    @Override
    public Optional<Candidate> ofIdentity(Username id) {
        final TypedQuery<Candidate> query = entityManager().createQuery(
                "SELECT d1 FROM Candidate d1 WHERE d1.user.username = :id", Candidate.class);
        query.setParameter("id", id);
        return query.getResultList().stream().findFirst();
    }

    @Override
    public Iterable<Candidate> activeCandidates() {
        final TypedQuery<Candidate> query = entityManager().createQuery(
                "SELECT d1 FROM Candidate d1 WHERE d1.user.active = true", Candidate.class);
        return query.getResultList();
    }

    @Override
    public Iterable<Candidate> inactiveCandidates() {
        final TypedQuery<Candidate> query = entityManager().createQuery(
                "SELECT d1 FROM Candidate d1 WHERE d1.user.active = false", Candidate.class);
        return query.getResultList();
    }
}