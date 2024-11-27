package eapli.base.interviewmanagement.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.functional.Either;
import jakarta.persistence.Embeddable;

import javax.xml.bind.annotation.XmlAttribute;

@Embeddable
public class NumberOfVacancies implements ValueObject, DomainEntity<Integer> {
    private static final long serialVersionUID = 1L;
    @XmlAttribute
    @JsonProperty
    private Integer number;

    public NumberOfVacancies(int number) {
        this.number = number;
    }

    public NumberOfVacancies() {
        // for ORM
    }

    public static NumberOfVacancies valueOf(final int number) {
        return (NumberOfVacancies)tryValueOf(number).rightValueOrElseThrow(IllegalArgumentException::new);
    }

    public static Either<Integer, NumberOfVacancies> tryValueOf(final int number) {
        return number > 0 ? Either.right(new NumberOfVacancies(number)) : Either.left(-1);
    }

    public int value() {
        return this.number;
    }

    public String toString() {
        return String.valueOf(this.number);
    }

    public int compareTo(final NumberOfVacancies o) {
        return Integer.compare(this.number, o.number);
    }

    public boolean equals(final NumberOfVacancies o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Integer identity() {
        return number;
    }
}
