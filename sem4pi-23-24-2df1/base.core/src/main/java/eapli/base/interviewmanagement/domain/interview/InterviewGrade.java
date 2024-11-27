package eapli.base.interviewmanagement.domain.interview;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
public class InterviewGrade implements ValueObject, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Double grade;

    public InterviewGrade(Double grade) {
        this.grade = grade;
    }

    public InterviewGrade() {
        // for ORM
        this.grade = 0.0;
    }

    public Double grade() {
        return grade;
    }

    public void incrementGrade(Double grade) {
        if (this.grade == null || this.grade == -1.0) new InterviewGrade(grade);
        else this.grade += grade;
    }

    public void gradeInPercentage(InterviewGrade totalGrade) {
        this.grade = (this.grade / totalGrade.grade) * 100;
    }

    public void reset(){
        this.grade = 0.0;
    }
}
