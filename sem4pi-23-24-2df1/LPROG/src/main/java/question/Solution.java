package question;

import java.util.Objects;

public class Solution {
    private final String solution;
    private final Double value;

    public Solution(String solution, Double value) {
        this.solution = solution;
        this.value = value;
    }

    public String solutions() {
        return this.solution;
    }

    public Double value() {
        return this.value;
    }


    @Override
    public String toString() {
        return "Solution{" +
                "solution='" + solution + '\'' +
                ", value=" + value +
                '}';
    }
}
