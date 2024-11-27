package domain;

import static domain.MessageCodeType.*;

public enum MessageCode {

    COMMTEST(REQUEST, 0),
    DISCONN(RESPONSE, 1),
    ACK(RESPONSE, 2),
    ERR(RESPONSE, 3),
    AUTH(REQUEST, 4),

    LIST_CANDIDATE_APPLICATIONS(REQUEST, 5),
    LIST_CUSTOMER_ALL_JOB_OPENING(REQUEST, 6),

    CUSTOMER_NOTIFICATION(RESPONSE, 8);

    private final MessageCodeType type;
    private final int code;

    MessageCode(MessageCodeType messageCodeType, int i) {
        this.type = messageCodeType;
        this.code = i;
    }

    public MessageCodeType type() {
        return type;
    }

    public int code() {
        return code;
    }

    public static MessageCode withCode(int code) {
        for (MessageCode messageCode : values()) {
            if (messageCode.code == code) {
                return messageCode;
            }
        }
        return null;
    }
}