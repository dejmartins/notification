package africa.semicolon.notification.sms;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SmsRequest {

    private String phoneNumber;
    private String message;
}
