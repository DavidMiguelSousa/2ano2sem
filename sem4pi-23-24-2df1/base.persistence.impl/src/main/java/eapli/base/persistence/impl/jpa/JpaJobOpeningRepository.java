package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.candidatemanagement.domain.Candidate;
import eapli.base.clientusermanagement.domain.Customer;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.clientusermanagement.domain.jobopening.JobReference;
import eapli.base.clientusermanagement.repositories.JobOpeningRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jakarta.persistence.TypedQuery;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class JpaJobOpeningRepository
        extends JpaAutoTxRepository<JobOpening, JobReference, JobReference>
        implements JobOpeningRepository {

    public JpaJobOpeningRepository(final TransactionalContext autoTx) {
        super(autoTx, "jobReference");
    }

    public JpaJobOpeningRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "jobReference");
    }
    @Override
    public Iterable<JobOpening> findAllActive() {
        final TypedQuery<JobOpening> query = entityManager().createQuery(
                "SELECT d1 FROM JobOpening d1 WHERE d1.status = 1", JobOpening.class);

        return query.getResultList();
    }

    @Override
    public Iterable<JobOpening> jobOpeningsByCustomer(Customer customer) {
        final TypedQuery<JobOpening> query = entityManager().createQuery(
                "SELECT d1 FROM JobOpening d1 WHERE d1.customer = :customer", JobOpening.class);

        query.setParameter("customer", customer);
        return query.getResultList();
    }

    @Override
    public int numberOfApplicants(JobOpening jobOpening) {
        final TypedQuery<Long> query = entityManager().createQuery(
                "SELECT COUNT(d1) FROM JobApplication d1 WHERE d1.jobOpening = :jobOpening", Long.class);

        query.setParameter("jobOpening", jobOpening);
        return query.getResultList().size();
    }


    @Override
    public Iterable<JobOpening> findAll() {
        final TypedQuery<JobOpening> query = entityManager().createQuery(
                "SELECT d1 FROM JobOpening d1", JobOpening.class);

        return query.getResultList();
    }

    @Override
    public Optional<JobOpening> findByJobReference(JobReference reference) {
        final Map<String, Object> params = new HashMap<>();
        params.put("reference", reference);
        return matchOne("e.jobReference=:reference", params);
    }

    @Override
    public int findLastRecord() {
        final String jpql = "SELECT j.jobReference.reference FROM JobOpening j ORDER BY SUBSTRING(j.jobReference.reference, LOCATE('_', j.jobReference.reference) + 1) DESC";
        final TypedQuery<String> query = entityManager().createQuery(jpql, String.class);
        query.setMaxResults(1);
        String lastId = query.getSingleResult();
        return extractNumberFromId(lastId);
    }

    private int extractNumberFromId(String id) {
        int underscoreIndex = id.lastIndexOf("-");
        String numberStr = id.substring(underscoreIndex + 1);
        if (numberStr.length() == 1) {
            numberStr = "00" + numberStr;
        } else if (numberStr.length() == 2) {
            numberStr = "0" + numberStr;
        }
        return Integer.parseInt(numberStr);
}

}
