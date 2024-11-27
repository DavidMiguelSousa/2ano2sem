package requirements;


import java.util.Objects;

public class RequirementAnswer extends Requirement {
    private final String answer;

    public RequirementAnswer(Requirement question, String answer) {
        super(question.question(), question.type());
        this.answer = answer;

    }
    public RequirementAnswer(String question, RequirementType type, String answer) {
        super(question, type);
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        question.QuestionAnswer that = (question.QuestionAnswer) o;
        return Objects.equals(answer, that.answer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), answer);
    }

    @Override
    public String toString() {
        return "RequirementAnswer{" +
                "answer='" + answer + '\'' +
                '}';
    }


    public String answer() {
        return this.answer;
    }
}

