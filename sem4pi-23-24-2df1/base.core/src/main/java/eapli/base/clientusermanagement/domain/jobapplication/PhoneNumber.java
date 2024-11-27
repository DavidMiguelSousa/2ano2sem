package eapli.base.clientusermanagement.domain.jobapplication;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
public class PhoneNumber implements ValueObject, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String phoneNumber;

    public PhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    protected PhoneNumber() {
        // for ORM
    }

    public String number() {
        return this.phoneNumber;
    }

    public void updatePhoneNumber(String phoneNumber) {
        if (validateNumber(phoneNumber)) this.phoneNumber = phoneNumber;
        else throw new IllegalArgumentException("Invalid portuguese phone number");
    }

    private boolean validateNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty() || phoneNumber.isBlank()) {
            return false;
        } else return phoneNumber.matches("^9\\d{2}[ -]?\\d{3}[ -]?\\d{3}$");
    }

    @Override
    public String toString() {
        return this.phoneNumber;
    }
}
