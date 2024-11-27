package requirements;

public enum RequirementToken {
    JOBREQUIREMENTS("JOB REQUIREMENTS"),
    REQUIREMENT_TOKEN("Requirement"),
    ANSWER("Answer");

    private final String token;

    RequirementToken(String token) {
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

