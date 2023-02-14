package africa.semicolon.notification.sender;

import africa.semicolon.notification.dtos.requests.MessageRequest;
import africa.semicolon.notification.utils.SendType;
import africa.semicolon.notification.utils.Sender;
import com.google.i18n.phonenumbers.NumberParseException;
import com.mashape.unirest.http.exceptions.UnirestException;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SenderService {

    private final SenderFactory senderFactory;

    public void send(MessageRequest messageRequest) throws MessagingException, IOException, NumberParseException, UnirestException {
        messageRequest.setReference(generateReference());
        Sender sender = senderFactory.getSender(SendType.fromString(messageRequest.getType()));
        sender.send(messageRequest);

    }

    private String generateReference(){
        return UUID.randomUUID().toString();
    };

}