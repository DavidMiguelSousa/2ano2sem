package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.candidatemanagement.domain.Candidate;
import eapli.base.clientusermanagement.domain.ApprovalStatus;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplicationReference;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.clientusermanagement.domain.jobopening.JobReference;
import eapli.base.clientusermanagement.repositories.JobApplicationRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jakarta.persistence.TypedQuery;

class JpaJobApplicationRepository
        extends JpaAutoTxRepository<JobApplication, JobApplicationReference, JobApplicationReference>
        implements JobApplicationRepository {

    public JpaJobApplicationRepository(final TransactionalContext autoTx) {
        super(autoTx, "jobApplicationReference");
    }

    public JpaJobApplicationRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "jobApplicationReference");
    }

    @Override
    public Iterable<JobApplication> applicationsByCandidate(Candidate candidate) {
        final TypedQuery<JobApplication> query = entityManager().createQuery(
                "SELECT d1 FROM JobApplication d1 WHERE d1.candidate = :candidate", JobApplication.class);

        query.setParameter("candidate", candidate);
        return query.getResultList();
    }

    @Override
    public int numberOfApplicants(JobApplication application) {
        final TypedQuery<Long> query = entityManager().createQuery(
                "SELECT COUNT(d1) FROM JobApplication d1 WHERE d1.jobOpening = :jobOpening", Long.class);

        query.setParameter("jobOpening", application.jobOpening());
        return query.getResultList().size();
    }

    @Override
    public Iterable<JobApplication> applicationsByJobOpening(JobOpening jobOpening) {
        JobReference reference = jobOpening.jobReference();
        final TypedQuery<JobApplication> query = entityManager().createQuery(
                "SELECT d1 FROM JobApplication d1 WHERE d1.jobOpening.jobReference = :reference", JobApplication.class);

        query.setParameter("reference", reference);
        return query.getResultList();
    }

    @Override
    public Iterable<JobApplication> findByStatus(ApprovalStatus status) {
        return match("e.status=:status", status);
    }
}
