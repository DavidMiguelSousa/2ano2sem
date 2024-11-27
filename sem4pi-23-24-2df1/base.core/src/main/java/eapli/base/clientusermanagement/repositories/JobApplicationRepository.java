package eapli.base.clientusermanagement.repositories;

import eapli.base.candidatemanagement.domain.Candidate;
import eapli.base.clientusermanagement.domain.ApprovalStatus;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplicationReference;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.framework.domain.repositories.DomainRepository;

public interface JobApplicationRepository extends DomainRepository<JobApplicationReference, JobApplication> {

    Iterable<JobApplication> applicationsByJobOpening(JobOpening jobOpening);

    Iterable<JobApplication> findByStatus(ApprovalStatus status);

    Iterable<JobApplication> findAll();

    Iterable<JobApplication> applicationsByCandidate(Candidate candidate);

    int numberOfApplicants(JobApplication application);
}
