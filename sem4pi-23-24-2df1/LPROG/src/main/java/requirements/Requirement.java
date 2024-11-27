package requirements;

import question.QuestionType;

import java.util.Objects;

public class Requirement {
    private final String question;
    private final RequirementType type;

    public Requirement(String question, RequirementType type) {
        this.question = question;
        this.type = type;
    }

    public String question() {
        return this.question;
    }

    public RequirementType type() {
        return this.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, type);
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
        ", type=" + type +
        '}';
    }
}

