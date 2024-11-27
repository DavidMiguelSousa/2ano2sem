package eapli.base.clientusermanagement.repositories;

import eapli.base.candidatemanagement.domain.Candidate;
import eapli.base.clientusermanagement.domain.Customer;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.clientusermanagement.domain.jobopening.JobReference;
import eapli.base.clientusermanagement.domain.jobopening.Phase;
import eapli.base.clientusermanagement.domain.jobopening.Status;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface JobOpeningRepository extends DomainRepository<JobReference, JobOpening> {
    default Optional<JobOpening> findByJobReference(final JobReference reference) {
        return ofIdentity(reference);
    }

    Iterable<JobOpening> jobOpeningsByCustomer(Customer customer);

    Iterable<JobOpening> findAllActive();

    Iterable<JobOpening> findAll();

    int numberOfApplicants(JobOpening jobOpening);



    int findLastRecord();
}
