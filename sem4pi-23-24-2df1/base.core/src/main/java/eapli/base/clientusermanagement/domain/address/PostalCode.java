package eapli.base.clientusermanagement.domain.address;

import eapli.framework.domain.model.DomainEntity;
import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

@Embeddable
public class PostalCode implements ValueObject, DomainEntity<String> {

    private String postalCode;

    public PostalCode(String postalCode) {
        if (postalCode == null || postalCode.isEmpty()) {
            throw new IllegalArgumentException("Postal Code should neither be null nor empty");
        } else if (!postalCode.matches("\\d{4}-\\d{3}")) {
            throw new IllegalArgumentException("Postal Code should be in the format XXXX-XXX");
        } else {
            this.postalCode = postalCode;
        }
    }

    public PostalCode() {
        // for ORM
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostalCode that)) return false;
        return postalCode.equals(that.postalCode);
    }

    @Override
    public int hashCode() {
        return postalCode.hashCode();
    }

    @Override
    public boolean sameAs(Object other) {
        return this.equals(other);
    }

    public static PostalCode valueOf(String postalCode) {
        return new PostalCode(postalCode);
    }


    public String postalCode() {
        return this.postalCode;
    }

    @Override
    public String identity() {
        return this.postalCode;
    }
}
