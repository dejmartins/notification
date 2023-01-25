package africa.semicolon.notification.whatsapp;

import africa.semicolon.notification.utils.Sender;
import africa.semicolon.notification.dtos.requests.MessageRequest;
import africa.semicolon.notification.whatsapp.twilio.WhatsappMessageSender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class WhatsappService {

    private final Sender sender;

    @Autowired
    public WhatsappService(@Qualifier("TwilioWhatsapp")WhatsappMessageSender whatsappMessageSender){
        this.sender = whatsappMessageSender;
    }

    public void send(MessageRequest messageRequest) throws IOException {
        sender.send(messageRequest);
    }
}
