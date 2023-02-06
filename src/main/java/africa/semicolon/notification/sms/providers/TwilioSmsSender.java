package africa.semicolon.notification.sms.providers;

import africa.semicolon.notification.config.twilio.TwilioConfiguration;
import africa.semicolon.notification.dtos.requests.MessageRequest;
import africa.semicolon.notification.sms.SmsRequest;
import africa.semicolon.notification.sms.SmsService;
import africa.semicolon.notification.sms.mapper.SmsModelMapper;
import africa.semicolon.notification.utils.Sender;
import com.google.i18n.phonenumbers.NumberParseException;
import com.twilio.rest.api.v2010.account.Message;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class TwilioSmsSender implements SmsService {

    private final TwilioConfiguration twilioConfiguration;
    private SmsModelMapper mapper;

    @Override
    public void send(MessageRequest messageRequest) throws NumberParseException {
        SmsRequest smsRequest = mapper.map(messageRequest);
        String senderId = "";
        messageRequest.setPhoneNumber(Sender.phoneNumberFormat(messageRequest.getPhoneNumber()));
        if (Sender.isPhoneNumberValid(smsRequest.getPhoneNumber())) {
            Message message = Message.creator(
                            new com.twilio.type.PhoneNumber(smsRequest.getPhoneNumber()),
                            new com.twilio.type.PhoneNumber(twilioConfiguration.getTrialNumber()),
                            smsRequest.getMessage())
                    .create();
            senderId = message.getSid();
        } else {
            throw new IllegalArgumentException("Invalid Phone Number for Nigeria");
        }
    }
}
