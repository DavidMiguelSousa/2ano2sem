package domain;

public enum MessageCodeType {
    REQUEST("Request"),
    RESPONSE("Response");

    private final String type;

    private MessageCodeType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
