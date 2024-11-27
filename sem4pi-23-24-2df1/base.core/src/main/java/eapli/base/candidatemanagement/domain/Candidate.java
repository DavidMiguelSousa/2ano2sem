package eapli.base.candidatemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Name;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import jakarta.persistence.*;

import java.io.Serial;
import java.util.Calendar;

@Entity
//@Table(name = "CANDIDATE")
public class Candidate implements AggregateRoot<Username> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    /**
     * cascade = CascadeType.NONE as the systemUser is part of another aggregate
     */
    @OneToOne(optional = false)
    private SystemUser user;

    @Id
    @GeneratedValue
    private Long id;

    Candidate(SystemUser user) {
        this.user = user;
    }

    protected Candidate() {
        // for ORM only
    }

    public Name name() {
        return user.name();
    }

    public EmailAddress email() {
        return user.email();
    }

    public SystemUser user() {
        return user;
    }

    public boolean isActive() {
        return user.isActive();
    }

    public void activate() {
        user.activate();
    }

    public void deactivate() {
        user.deactivate(Calendar.getInstance());
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Username identity() {
        return user.identity();
    }

}