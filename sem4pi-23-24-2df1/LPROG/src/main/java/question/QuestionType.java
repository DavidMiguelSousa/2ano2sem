package question;

public enum QuestionType {
    TFQUES("True or false question", "TFQUES [TRUE|FALSE]"),
    SAQUES("Short answer question", "SAQUES [Text]"),
    SCQUES("Single choice question", "SCQUES [Option]"),
    MCQUES("Multiple choice question", "MCQUES [Options]"),
    INTQUES("Integer question", "INTQUES [Integer]"),
    DECQUES("Decimal question", "DECQUES [Decimal]"),
    DATEQUES("Date question", "DATEQUES [DD/MM/YYYY]"),
    TIMEQUES("Time question", "TIMEQUES [HH:MM]"),
    NSQUES("Numeric scale (1-5) question", "NSQUES [1-5]");

    private final String questionType;
    private final String token;

    QuestionType(String questionType, String token) {
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
