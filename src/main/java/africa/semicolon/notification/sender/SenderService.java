package africa.semicolon.notification.sender;

import africa.semicolon.notification.dtos.requests.MessageRequest;
import africa.semicolon.notification.utils.Sender;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SenderService {

    private final SenderFactory senderFactory;

    public void send(MessageRequest messageRequest) throws MessagingException, IOException {
        Sender sender = senderFactory.getSender(messageRequest.getType());
        sender.send(messageRequest);
    }

}