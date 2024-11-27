package eapli.base.clientusermanagement.repositories;

import eapli.base.clientusermanagement.domain.jobopening.JobRequirements;
import eapli.base.interviewmanagement.domain.InterviewModel;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.general.domain.model.Designation;

public interface JobRequirementsRepository extends DomainRepository<Designation, JobRequirements> {

    Iterable<JobRequirements> findAll();
    Iterable<JobRequirements> findByDesignation(Designation designation);

}
