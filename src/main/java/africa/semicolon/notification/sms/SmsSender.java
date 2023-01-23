package africa.semicolon.notification.sms;

import java.io.IOException;

public interface SmsSender {

    void send(SmsRequest smsRequest) throws IOException;
}
