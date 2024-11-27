package eapli.base.clientusermanagement.domain.jobopening;

import eapli.framework.domain.model.DomainEntities;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.time.domain.model.DateInterval;
import eapli.framework.time.util.Calendars;
import jakarta.persistence.Embeddable;

import java.util.Calendar;

@Embeddable
public class PhaseDetails {
    private DateInterval dateInterval;
    private Status status;

    public PhaseDetails(DateInterval dateInterval, Status status) {
        this.dateInterval = dateInterval;
        this.status = status;
    }

    public PhaseDetails(Status status){
        this.dateInterval = dateInterval();
        this.status = status;
    }

    public PhaseDetails() {
        // for ORM
    }

    public DateInterval dateInterval() {
        return this.dateInterval;
    }

    public Status status() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        return DomainEntities.areEqual((DomainEntity<?>) this, o);
    }

    public void updateStatus(Status status) {
        this.status = status;
    }
}
