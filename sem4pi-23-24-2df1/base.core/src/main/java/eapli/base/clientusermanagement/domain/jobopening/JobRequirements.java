package eapli.base.clientusermanagement.domain.jobopening;

import eapli.base.clientusermanagement.domain.jobopening.dto.JobRequirementsDTO;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.representations.dto.DTOable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import java.io.Serial;

@Entity
public class JobRequirements implements  AggregateRoot<Designation>, DTOable<JobRequirementsDTO> {
    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private Designation designation;

    private Description description;

    public JobRequirements(Designation designation, Description description) {
        this.designation = designation;
        this.description = description;
    }

    protected JobRequirements() {
        // for ORM only
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Designation identity() {
        return this.designation;
    }

    public Description description() {
        return this.description;
    }

    @Override
    public JobRequirementsDTO toDTO() {
        return new JobRequirementsDTO(designation, description);
    }

}
