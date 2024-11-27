package eapli.base.interviewmanagement.domain.interview;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
public class Interview implements ValueObject, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private InterviewDate date;
    private InterviewGrade grade;
    private Designation designation;
    private Description description;

    public Interview(InterviewDate date, Designation designation, Description description) {
        this.date = date;
        this.grade = new InterviewGrade();
        this.designation = designation;
        this.description = description;
    }

    public Interview() {
        this.grade = new InterviewGrade();
        this.date = new InterviewDate();
        //for ORM
    }

    public InterviewDate date() {
        return date;
    }

    public InterviewGrade grade() {
        return grade;
    }

    public void defineGrade(InterviewGrade grade) {
        this.grade = grade;
    }

    public void defineDate(InterviewDate date) {
        this.date = date;
    }

    public boolean isPresent() {
        return grade != null && grade.grade() != -1.0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (this.getClass() != obj.getClass())) {
            return false;
        }

        final Interview other = (Interview) obj;
        return this.designation.equals(other.designation);
    }
}
