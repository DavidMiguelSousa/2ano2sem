package eapli.base.persistence.impl.inmemory;

import eapli.base.candidatemanagement.domain.Candidate;
import eapli.base.clientusermanagement.domain.ApprovalStatus;
import eapli.base.clientusermanagement.domain.Customer;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.clientusermanagement.domain.jobopening.JobReference;
import eapli.base.clientusermanagement.repositories.JobOpeningRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import jakarta.persistence.TypedQuery;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryJobOpeningRepository extends
        InMemoryDomainRepository<JobOpening, JobReference> implements JobOpeningRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Optional<JobOpening> findByJobReference(JobReference reference) {
        return matchOne(e -> e.identity().equals(reference));
    }

    @Override
    public Iterable<JobOpening> findAllActive() {
        return match(e -> e.status().equals(ApprovalStatus.ACCEPTED));
    }

    @Override
    public Iterable<JobOpening> jobOpeningsByCustomer(Customer customer) {
        return match(e -> e.customer().equals(customer));
    }

    @Override
    public int numberOfApplicants(JobOpening jobOpening) {
        Iterable<JobOpening> jobApplications = match(e -> e.jobReference().reference().equals(jobOpening.jobReference().reference()));
        AtomicInteger count = new AtomicInteger();
        jobApplications.forEach(e -> count.getAndIncrement());
        return count.get();
    }


    @Override
    public int findLastRecord() {
        JobOpening lastRecord = null;
        for (JobOpening jobOpening : data().values()) {
            JobReference currentReference = jobOpening.jobReference();
            if (lastRecord == null || isGreater(currentReference, lastRecord.jobReference())) {
                lastRecord = jobOpening;
            }
        }
        assert lastRecord != null;
        return extractNumber(lastRecord.jobReference());
    }

    private boolean isGreater(JobReference reference1, JobReference reference2) {
        // Se as palavras forem iguais, comparar os números após o "_" alfabeticamente
        int number1 = extractNumber(reference1);
        int number2 = extractNumber(reference2);
        return number1 > number2;

    }

    private int extractNumber(JobReference reference) {
        // Extrair o número após o "_" em JobReference e convertê-lo para inteiro
        String referenceStr = reference.toString();
        int underscoreIndex = referenceStr.lastIndexOf("_");
        String numberStr = referenceStr.substring(underscoreIndex + 1);
        return Integer.parseInt(numberStr);
    }
}
