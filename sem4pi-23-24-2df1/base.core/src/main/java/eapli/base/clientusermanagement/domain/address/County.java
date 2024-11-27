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
public class County implements ValueObject, DomainEntity<String> {
    private static final long serialVersionUID = 1L;
    @XmlAttribute
    @JsonProperty
    private String county;

    public County(String county) {
        this.county = county;
    }

    public County() {
        // for ORM
    }

    public static County valueOf(final String county) {
        return (County)tryValueOf(county).rightValueOrElseThrow(IllegalArgumentException::new);
    }

    public static Either<String, County> tryValueOf(final String county) {
        return CheckIf.succeeds(StringPredicates.isPhrase(county), () -> {
            return new County(county);
        }, () -> {
            return "County should neither be null nor empty nor have starting blank spaces";
        });
    }

    public String toString() {
        return this.county;
    }

    public int compareTo(final County o) {
        return this.county.compareTo(o.county);
    }

    public boolean equals(final County o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public String identity() {
        return county;
    }
}
