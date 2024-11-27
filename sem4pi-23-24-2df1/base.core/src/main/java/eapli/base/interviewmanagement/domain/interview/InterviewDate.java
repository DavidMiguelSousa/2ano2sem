package eapli.base.interviewmanagement.domain.interview;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;
import java.util.Calendar;

@Embeddable
public class InterviewDate implements ValueObject, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Calendar date;

    public InterviewDate(Calendar date) {
        this.date = date;
    }

    public InterviewDate() {
        //for ORM
    }

    public Calendar date() {
        return date;
    }
}
