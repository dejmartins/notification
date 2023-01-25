package africa.semicolon.notification.whatsapp.twilio;

import africa.semicolon.notification.utils.Sender;
import africa.semicolon.notification.config.twilio.TwilioConfiguration;
import africa.semicolon.notification.dtos.requests.MessageRequest;
import africa.semicolon.notification.dtos.responses.SendResponse;
import africa.semicolon.notification.whatsapp.WhatsappRequest;
import africa.semicolon.notification.whatsapp.mapper.WhatsappModelMapper;
import com.twilio.rest.api.v2010.account.Message;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service("TwilioWhatsapp")
@AllArgsConstructor
public class WhatsappMessageSender implements Sender {

    private TwilioConfiguration twilioConfiguration;
    private final WhatsappModelMapper mapper;

    @Override
    public CompletableFuture<SendResponse> send(MessageRequest messageRequest) throws IOException {
        WhatsappRequest whatsappRequest = mapper.map(messageRequest);
        log.info(twilioConfiguration.getWhatsappTrialNumber());
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber("whatsapp:"+whatsappRequest.getPhoneNumber()),
                        new com.twilio.type.PhoneNumber("whatsapp:"+twilioConfiguration.getWhatsappTrialNumber()),
                        whatsappRequest.getMessage())
                .create();

        return CompletableFuture.completedFuture(new SendResponse(
                HttpStatus.OK.value(),
                "Sender ID: "+message.getSid(),
                true
        ));
    }
}
