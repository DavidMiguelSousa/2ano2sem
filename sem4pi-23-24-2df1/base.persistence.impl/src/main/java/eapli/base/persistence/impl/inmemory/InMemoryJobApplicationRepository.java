package eapli.base.persistence.impl.inmemory;

import eapli.base.candidatemanagement.domain.Candidate;
import eapli.base.clientusermanagement.domain.ApprovalStatus;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplicationReference;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.clientusermanagement.repositories.JobApplicationRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryJobApplicationRepository
        extends InMemoryDomainRepository<JobApplication, JobApplicationReference>
        implements JobApplicationRepository {

    static {
        InMemoryInitializer.init();
    }


    @Override
    public Iterable<JobApplication> findByStatus(ApprovalStatus status) {
        return match(e -> e.status().equals(status));
    }

    @Override
    public Iterable<JobApplication> applicationsByCandidate(Candidate candidate) {
        return match(e -> e.candidate().equals(candidate));
    }

    @Override
    public int numberOfApplicants(JobApplication application) {
        Iterable<JobApplication> jobApplications = match(e -> e.jobOpening().jobReference().reference().equals(application.jobOpening().jobReference().reference()));
        AtomicInteger count = new AtomicInteger();
        jobApplications.forEach(e -> count.getAndIncrement());
        return count.get();
    }

    @Override
    public Iterable<JobApplication> applicationsByJobOpening(JobOpening JobOpeningReference) {
        return match(e -> e.jobOpening().jobReference().reference().equals(JobOpeningReference.jobReference().reference()));
    }
}
