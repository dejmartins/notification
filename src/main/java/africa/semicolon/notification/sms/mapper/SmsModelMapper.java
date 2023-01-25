package africa.semicolon.notification.sms.mapper;

import africa.semicolon.notification.sms.SmsRequest;
import africa.semicolon.notification.dtos.requests.MessageRequest;
import org.springframework.stereotype.Component;

@Component
public class SmsModelMapper {

    public SmsRequest map(MessageRequest messageRequest) {
        SmsRequest smsRequest = new SmsRequest();
        smsRequest.setPhoneNumber(messageRequest.getPhoneNumber());
        smsRequest.setMessage(messageRequest.getMessage());
        return smsRequest;
    }
}
