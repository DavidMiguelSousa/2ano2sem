package eapli.base.services;

import eapli.base.candidatemanagement.domain.Candidate;
import eapli.base.candidatemanagement.domain.CandidateBuilder;
import eapli.base.candidatemanagement.repositories.CandidateRepository;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class CandidateManagementService {

    private final CandidateRepository candidateRepository;

    public CandidateManagementService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public Optional<Candidate> findCandidate(Username username) {
        return candidateRepository.ofIdentity(username);
    }

    public Iterable<Candidate> allCandidates() {
        return candidateRepository.findAll();
    }

    public Iterable<Candidate> activeCandidates() {
        return candidateRepository.activeCandidates();
    }

    public Iterable<Candidate> inactiveCandidates() {
        return candidateRepository.inactiveCandidates();
    }

    public Candidate registerCandidate(SystemUser user) {
        CandidateBuilder candidateBuilder = new CandidateBuilder();
        candidateBuilder.with(user);
        return candidateRepository.save(candidateBuilder.build());
    }

    public Optional<Candidate> candidateWithUsername(Username username) {
        return candidateRepository.ofIdentity(username);
    }

    @Transactional
    public Candidate enableCandidate(final Candidate candidate) {
        if (candidate == null) {
            throw new IllegalArgumentException("Candidate doesn't exist.");
        }

        if (candidate.isActive()) {
            throw new IntegrityViolationException("Candidate is already active.");
        } else {
            candidate.activate();
        }

        return candidateRepository.save(candidate);
    }

    @Transactional
    public Candidate disableCandidate(final Candidate candidate) {
        if (candidate == null) {
            throw new IllegalArgumentException("Candidate doesn't exist.");
        }

        if (!candidate.isActive()) {
            throw new IntegrityViolationException("Candidate is already inactive.");
        } else {
            candidate.deactivate();
        }

        return candidateRepository.save(candidate);
    }
}
