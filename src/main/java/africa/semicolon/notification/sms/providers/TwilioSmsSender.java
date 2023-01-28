package africa.semicolon.notification.sms.providers;

import africa.semicolon.notification.config.twilio.TwilioConfiguration;
import africa.semicolon.notification.dtos.requests.MessageRequest;
import africa.semicolon.notification.sms.SmsRequest;
import africa.semicolon.notification.sms.SmsService;
import africa.semicolon.notification.sms.mapper.SmsModelMapper;
import africa.semicolon.notification.utils.SendType;
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
    public SendType getType() {
        return SendType.SMS;
    }

    @Override
    public void send(MessageRequest messageRequest) {
        SmsRequest smsRequest = mapper.map(messageRequest);
        String senderId = "";
        if (isPhoneNumberValid(smsRequest.getPhoneNumber())) {
            Message message = Message.creator(
                            new com.twilio.type.PhoneNumber(smsRequest.getPhoneNumber()),
                            new com.twilio.type.PhoneNumber(twilioConfiguration.getTrialNumber()),
                            smsRequest.getMessage())
                    .create();
            senderId = message.getSid();
        }

        log.info(senderId);
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        //Todo: Google library to validate phone numbers
        return true;
    }
}
