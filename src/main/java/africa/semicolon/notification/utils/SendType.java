package africa.semicolon.notification.utils;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum SendType {
    SMS("sms"), EMAIL("email"), WHATSAPP("whatsapp");

    private final String sendType;

    SendType(String sendType) {
        this.sendType = sendType;
    }

    public String getSendType() {
        return this.sendType;
    }

    @JsonCreator
    public static SendType fromString(String sendType) {
        return SendType.valueOf(sendType.toUpperCase());
    }
}
