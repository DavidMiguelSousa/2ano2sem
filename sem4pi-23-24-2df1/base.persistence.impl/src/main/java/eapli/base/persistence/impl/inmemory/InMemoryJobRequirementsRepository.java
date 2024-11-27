package eapli.base.persistence.impl.inmemory;

import eapli.base.clientusermanagement.domain.jobopening.JobRequirements;
import eapli.base.clientusermanagement.repositories.JobRequirementsRepository;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryJobRequirementsRepository
        extends InMemoryDomainRepository<JobRequirements, Designation>
        implements JobRequirementsRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<JobRequirements> findByDesignation(Designation name) {
        return match(e -> e.identity().equals(name));
    }

}
