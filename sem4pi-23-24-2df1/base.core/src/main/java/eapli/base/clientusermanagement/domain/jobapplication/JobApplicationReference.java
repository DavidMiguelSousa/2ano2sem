package eapli.base.clientusermanagement.domain.jobapplication;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class JobApplicationReference implements ValueObject, Comparable<JobApplicationReference> {

    private static final long serialVersionUID = 1L;
    private String jobApplicationReference;

    public JobApplicationReference(String jobApplicationReference) {
        this.jobApplicationReference = jobApplicationReference;
    }

    protected JobApplicationReference() {
        // for ORM
    }

    public static JobApplicationReference valueOf(String s) {
        return new JobApplicationReference(s);
    }

    public String jobApplicationReference() {
        return this.jobApplicationReference;
    }

    @Override
    public String toString() {
        return jobApplicationReference;
    }

    @Override
    public int compareTo(JobApplicationReference other) {
        return jobApplicationReference.compareTo(other.jobApplicationReference);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobApplicationReference that = (JobApplicationReference) o;
        return Objects.equals(jobApplicationReference, that.jobApplicationReference);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(jobApplicationReference);
    }
}
