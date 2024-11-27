package question;

import java.util.Objects;

public class Question {
    private final String question;
    private final QuestionType type;
    private final int grade;

    public Question(String question, int grade, QuestionType type) {
        this.question = question;
        this.grade = grade;
        this.type = type;
    }

    public String question() {
        return this.question;
    }

    public int value() {
        return this.grade;
    }

    public QuestionType type() {
        return this.type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question obj = (Question) o;
        return grade == obj.grade && Objects.equals(question, obj.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, grade);
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", grade=" + grade +
                '}';
    }
}
