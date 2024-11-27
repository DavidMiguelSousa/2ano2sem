package eapli.base.interviewmanagement.domain;

import eapli.base.interviewmanagement.dto.InterviewModelDTO;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.representations.dto.DTOable;
import jakarta.persistence.*;

import java.io.Serial;

@Entity
public class InterviewModel implements AggregateRoot<Designation>, DTOable<InterviewModelDTO> {
    @Serial
    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @EmbeddedId
    private Designation name;

    private Description description;

    public InterviewModel(Designation name, Description description) {
        this.name = name;
        this.description = description;
    }

    public InterviewModel(Designation name) {
        this.name = name;
    }

    protected InterviewModel() {
        // for ORM
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Designation identity() {
        return this.name;
    }

    public Description description() {
        return this.description;
    }

    public void updateDescription(Description description) {
        this.description = description;
    }

    @Override
    public InterviewModelDTO toDTO() {
        return new InterviewModelDTO(this.identity(), this.description());
    }
}