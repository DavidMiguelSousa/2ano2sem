package question;

import java.util.Objects;

public class QuestionAnswer extends Question{
    private final String answer;

    public QuestionAnswer(Question question, String answer) {
        super(question.question(), question.value(), question.type());
        this.answer = answer;

    }
    public QuestionAnswer(String question, int value, QuestionType type, String answer) {
        super(question, value, type);
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        QuestionAnswer that = (QuestionAnswer) o;
        return Objects.equals(answer, that.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), answer);
    }

    @Override
    public String toString() {
        return "QuestionAnswer{" +
                "answer='" + answer + '\'' +
                '}';
    }


    public String answer() {
        return this.answer;
    }
}
