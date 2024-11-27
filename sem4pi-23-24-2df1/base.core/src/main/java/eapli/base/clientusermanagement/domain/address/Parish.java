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
public class Parish implements ValueObject, DomainEntity<String> {
    private static final long serialVersionUID = 1L;
    @XmlAttribute
    @JsonProperty
    private String parish;

    public Parish(String parish) {
        this.parish = parish;
    }

    public Parish() {
        // for ORM
    }

    public static Parish valueOf(final String parish) {
        return (Parish)tryValueOf(parish).rightValueOrElseThrow(IllegalArgumentException::new);
    }

    public static Either<String, Parish> tryValueOf(final String parish) {
        return CheckIf.succeeds(StringPredicates.isPhrase(parish), () -> {
            return new Parish(parish);
        }, () -> {
            return "Parish should neither be null nor empty nor have starting blank spaces";
        });
    }

    public String toString() {
        return this.parish;
    }

    public int compareTo(final Parish o) {
        return this.parish.compareTo(o.parish);
    }

    public boolean equals(final Parish o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public String identity() {
        return parish;
    }
}
