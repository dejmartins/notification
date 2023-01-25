package africa.semicolon.notification.sms.mapper;

import africa.semicolon.notification.sms.SmsRequest;
import africa.semicolon.notification.utils.dtos.requests.MessageRequest;

public class ModelMapper {

    public SmsRequest map(MessageRequest messageRequest) {
        SmsRequest smsRequest = new SmsRequest();
        smsRequest.setPhoneNumber(messageRequest.getPhoneNumber());
        smsRequest.setMessage(messageRequest.getMessage());
        return smsRequest;
    }
}
