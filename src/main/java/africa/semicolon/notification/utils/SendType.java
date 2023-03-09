package africa.semicolon.notification.utils;

import africa.semicolon.notification.exceptions.InvalidSendTypeException;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;
import java.util.List;

public enum SendType {
  SMS("sms"),
  EMAIL("email"),
  WHATSAPP("whatsapp");

  private final String sendType;

  SendType(String sendType) {
    this.sendType = sendType;
  }

  public String getSendType() {
    return this.sendType;
  }

  @JsonCreator
  public static SendType fromString(String sendType) {
    List<SendType> types =
        Arrays.stream(SendType.values())
            .filter((type) -> type.toString().equalsIgnoreCase(sendType))
            .toList();
    if (!types.isEmpty()) {
      return types.get(0);
    }
    throw new InvalidSendTypeException("Invalid Type");
  }
}
