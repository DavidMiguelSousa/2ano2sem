package eapli.base.clientusermanagement.domain.address;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Address {
    private District district;
    private County county;
    private Parish parish;
    private Street street;
    private DoorNumber doorNumber;
    private PostalCode postalCode;

    public Address(District district, County county, Parish parish, Street street, DoorNumber doorNumber, PostalCode postalCode) {
        this.district = district;
        this.county = county;
        this.parish = parish;
        this.street = street;
        this.doorNumber = doorNumber;
        this.postalCode = postalCode;
    }

    public Address() {
        //for ORM
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return district == address.district && Objects.equals(county, address.county) && Objects.equals(parish, address.parish) && Objects.equals(street, address.street) && Objects.equals(postalCode, address.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(district, county, parish, street, postalCode);
    }

    public District district() {
        return this.district;
    }

    public County county() {
        return this.county;
    }

    public Parish parish() {
        return this.parish;
    }

    public Street street() {
        return this.street;
    }

    public DoorNumber doorNumber() {
        return this.doorNumber;
    }

    public PostalCode postalCode() {
        return this.postalCode;
    }
}
