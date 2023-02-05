package africa.semicolon.notification.sender;

import africa.semicolon.notification.dtos.requests.MessageRequest;
import africa.semicolon.notification.exceptions.InvalidSendTypeException;
import africa.semicolon.notification.utils.SendType;
import africa.semicolon.notification.utils.Sender;
import com.google.i18n.phonenumbers.NumberParseException;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SenderService {

    private final SenderFactory senderFactory;

    public void send(MessageRequest messageRequest) throws MessagingException, IOException, NumberParseException {
        messageRequest.setReference(generateReference());
        try{
            Sender sender = senderFactory.getSender(SendType.fromString(messageRequest.getType()));
            sender.send(messageRequest);
        } catch (Exception e) {
            throw new InvalidSendTypeException("Invalid Type");
        }

    }

    private String generateReference(){
        return UUID.randomUUID().toString();
    };

}