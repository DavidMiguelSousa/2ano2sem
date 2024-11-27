package question;

import java.util.List;
import java.util.Objects;

public class QuestionSolution extends Question{
    List<Solution> solutions;

    public QuestionSolution(Question question, List<Solution> solutions) {
        super(question.question(), question.value(), question.type());
        this.solutions = solutions;
    }

    public List<Solution> solutions() {
        return solutions;
    }

    public Solution onlySolution(){
        if (solutions.isEmpty()) {
            return new Solution("", 0.0);
        }else{
            return solutions.get(0);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        QuestionSolution that = (QuestionSolution) o;
        return Objects.equals(solutions, that.solutions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), solutions);
    }

    @Override
    public String toString() {
        return "QuestionSolution{" +
                "solutions=" + solutions +
                '}';
    }
}
