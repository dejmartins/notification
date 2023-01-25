package africa.semicolon.notification.whatsapp;

import africa.semicolon.notification.dtos.responses.SendResponse;
import africa.semicolon.notification.utils.Sender;
import africa.semicolon.notification.dtos.requests.MessageRequest;
import africa.semicolon.notification.whatsapp.twilio.WhatsappMessageSender;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class WhatsappService implements Sender{

    private final Sender sender;

    @Autowired
    public WhatsappService(@Qualifier("TwilioWhatsapp") WhatsappMessageSender whatsappMessageSender){
        this.sender = whatsappMessageSender;
    }

    public CompletableFuture<SendResponse> send(MessageRequest messageRequest) throws IOException {
        return sender.send(messageRequest);
    }
}
