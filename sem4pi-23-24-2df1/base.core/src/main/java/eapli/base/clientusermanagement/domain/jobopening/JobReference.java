package eapli.base.clientusermanagement.domain.jobopening;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class JobReference implements ValueObject, Comparable<JobReference>  {

    private String reference;

    protected JobReference() {
        // for ORM
    }

    public JobReference(String reference) {
        this.reference = reference;
    }

    public String reference() {
        return this.reference;
    }
    @Override
    public String toString() {
        return this.reference;
    }
    @Override
    public int compareTo(JobReference o) {
        return this.reference.compareTo(o.reference);
    }

    public static JobReference valueOf(String reference) {
        //reference is of type XXXX-YYY, being X any alphanumeric character and Y a digit
        String[] customerCodeAndDigits = reference.split("-");
        if (reference.isEmpty() ||
        reference.isBlank() ||
        !customerCodeAndDigits[1].matches("\\d+")) {
            throw new IllegalArgumentException("Not a job reference.");
        }
        return new JobReference(reference);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobReference that = (JobReference) o;
        return Objects.equals(reference, that.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(reference);
    }
}
