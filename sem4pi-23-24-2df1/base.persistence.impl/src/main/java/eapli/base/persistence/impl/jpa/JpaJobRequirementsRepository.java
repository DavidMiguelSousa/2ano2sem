package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.clientusermanagement.domain.jobopening.JobRequirements;
import eapli.base.clientusermanagement.repositories.JobRequirementsRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jakarta.persistence.TypedQuery;

public class JpaJobRequirementsRepository
        extends JpaAutoTxRepository<JobRequirements, Designation, Designation>
        implements JobRequirementsRepository {

    public JpaJobRequirementsRepository(TransactionalContext autoTx) {
        super(autoTx, "designation");
    }

    public JpaJobRequirementsRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "designation");
    }

    @Override
    public Iterable<JobRequirements> findByDesignation(Designation designation) {
        final TypedQuery<JobRequirements> query = entityManager().createQuery(
                "SELECT d1 FROM JobRequirements d1 WHERE d1.designation = :name", JobRequirements.class);

        return query.getResultList();
    }
}
