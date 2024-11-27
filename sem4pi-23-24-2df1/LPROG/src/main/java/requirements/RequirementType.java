package requirements;

public enum RequirementType {
    MININT("Requirement minimum integer", "MININT"),
    MAXINT("Requirement maximum integer", "MAXINT"),
    MINORD("Requirement minimum ordinal", "MINORD"),
    MAXORD("Requirement maximum ordinal", "MAXORD"),
    MULORSING("Multiple or single choice", "MULORSING");

    private final String questionType;
    private final String token;

    RequirementType(String questionType, String token) {
        this.questionType = questionType;
        this.token = token;
    }

    public String type() {
        return questionType;
    }

    public String token() {
        return token;
    }

    @Override
    public String toString() {
        return questionType;
    }
}

