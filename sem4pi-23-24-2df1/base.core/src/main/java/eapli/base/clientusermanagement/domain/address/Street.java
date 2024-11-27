package eapli.base.clientusermanagement.domain.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.functional.Either;
import eapli.framework.strings.util.StringPredicates;
import eapli.framework.validations.CheckIf;
import jakarta.persistence.Embeddable;

import javax.xml.bind.annotation.XmlAttribute;

@Embeddable
public class Street implements ValueObject, DomainEntity<String> {
    private static final long serialVersionUID = 1L;
    @XmlAttribute
    @JsonProperty
    private String street;

    public Street(String street) {
        this.street = street;
    }

    public Street() {
        // for ORM
    }

    public static Street valueOf(final String street) {
        return (Street)tryValueOf(street).rightValueOrElseThrow(IllegalArgumentException::new);
    }

//    private static final List<String> VALID_PREFIXES = Arrays.asList(
//            "Rua", "Avenida", "Praça", "Praceta", "Travessa",
//            "R.", "Av.", "Pr.", "Tv."
//    );
//
//    private static Predicate<String> startsWithValidPrefix() {
//        return s -> VALID_PREFIXES.stream().anyMatch(s::startsWith);
//    }

    public static Either<String, Street> tryValueOf(final String street) {
        return CheckIf.succeeds(StringPredicates.isPhrase(street), () -> {
            return new Street(street);
        }, () -> {
            return "Street should neither be null nor empty nor have starting blank spaces";
        });
    }

    public String toString() {
        return this.street;
    }

    public int compareTo(final Street o) {
        return this.street.compareTo(o.street);
    }

    public boolean equals(final Street o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public String identity() {
        return street;
    }
}
