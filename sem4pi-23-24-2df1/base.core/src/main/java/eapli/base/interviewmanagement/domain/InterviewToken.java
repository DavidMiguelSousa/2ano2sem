package eapli.base.interviewmanagement.domain;

public enum InterviewToken {
    INTERVIEW("INTERVIEW"),
    OPTION("OPT"),
    SOLUTION("SOL");

    private final String token;

    InterviewToken(String token) {
        this.token = token;
    }

    public String token() {
        return token;
    }

    @Override
    public String toString() {
        return token;
    }
}
