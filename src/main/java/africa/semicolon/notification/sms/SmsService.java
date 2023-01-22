package africa.semicolon.notification.sms;

import africa.semicolon.notification.sms.twilio.TwilioSmsSender;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmsService {

    private final SmsSender smsSender;

    @Autowired
    public SmsService(@Qualifier("Twilio") TwilioSmsSender smsSender) {
        this.smsSender = smsSender;
    }

    public void sendSms(SmsRequest smsRequest) {
        smsSender.send(smsRequest);
    }

}
