package eapli.base.clientusermanagement.domain.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.functional.Either;
import jakarta.persistence.Embeddable;

import javax.xml.bind.annotation.XmlAttribute;

@Embeddable
public class DoorNumber implements ValueObject, DomainEntity<Integer> {
    private static final long serialVersionUID = 1L;
    @XmlAttribute
    @JsonProperty
    private Integer doorNumber;

    public DoorNumber(int doorNumber) {
        this.doorNumber = doorNumber;
    }

    public DoorNumber() {
        // for ORM
    }

    public static DoorNumber valueOf(final int doorNumber) {
        return (DoorNumber)tryValueOf(doorNumber).rightValueOrElseThrow(IllegalArgumentException::new);
    }

    public static Either<Integer, DoorNumber> tryValueOf(final int doorNumber) {
        return doorNumber >= 0 ? Either.right(new DoorNumber(doorNumber)) : Either.left(-1);
    }

    public String toString() {
        return String.valueOf(this.doorNumber);
    }

    public int compareTo(final DoorNumber o) {
        return Integer.compare(this.doorNumber, o.doorNumber);
    }

    public boolean equals(final DoorNumber o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Integer identity() {
        return doorNumber;
    }
}
