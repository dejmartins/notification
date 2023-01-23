package africa.semicolon.notification.sms.twilio;

import africa.semicolon.notification.sms.SmsRequest;
import africa.semicolon.notification.sms.SmsSender;
import com.twilio.rest.api.v2010.account.Message;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("Twilio")
@AllArgsConstructor
public class TwilioSmsSender implements SmsSender {

    @Autowired
    private final TwilioConfiguration twilioConfiguration;

    @Override
    public void send(SmsRequest smsRequest) {
        if(isPhoneNumberValid(smsRequest.getPhoneNumber())){
            Message message = Message.creator(
                            new com.twilio.type.PhoneNumber(smsRequest.getPhoneNumber()),
                            new com.twilio.type.PhoneNumber(twilioConfiguration.getTrialNumber()),
                            smsRequest.getMessage())
                    .create();

            log.info(message.getSid());
        }
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        return true;
    }
}
