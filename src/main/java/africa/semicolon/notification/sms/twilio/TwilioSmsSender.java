package africa.semicolon.notification.sms.twilio;

import africa.semicolon.notification.sms.mapper.SmsModelMapper;
import africa.semicolon.notification.utils.Sender;
import africa.semicolon.notification.config.twilio.TwilioConfiguration;
import africa.semicolon.notification.sms.SmsRequest;
import africa.semicolon.notification.dtos.requests.MessageRequest;
import africa.semicolon.notification.dtos.responses.SendResponse;
import com.twilio.rest.api.v2010.account.Message;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service("Twilio")
@AllArgsConstructor
public class TwilioSmsSender implements Sender {

    @Autowired
    private final TwilioConfiguration twilioConfiguration;
    private SmsModelMapper mapper;

    @Override
    public CompletableFuture<SendResponse> send(MessageRequest messageRequest) throws IOException {
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

        return CompletableFuture.completedFuture(new SendResponse(
                HttpStatus.OK.value(),
                "Sender Id: " + senderId,
                true
        ));
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        //Todo: Google library to validate phone numbers
        //Todo: Check email - if email is not valid? (Related to scheduler)
        return true;
    }
}
