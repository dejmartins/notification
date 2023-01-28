package africa.semicolon.notification.whatsapp.providers;

import africa.semicolon.notification.config.twilio.TwilioConfiguration;
import africa.semicolon.notification.dtos.requests.MessageRequest;
import africa.semicolon.notification.utils.Sender;
import africa.semicolon.notification.whatsapp.WhatsappRequest;
import africa.semicolon.notification.whatsapp.WhatsappService;
import africa.semicolon.notification.whatsapp.mapper.WhatsappModelMapper;
import com.google.i18n.phonenumbers.NumberParseException;
import com.twilio.rest.api.v2010.account.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TwilioWhatsappSender implements WhatsappService {

    private final TwilioConfiguration twilioConfiguration;
    private final WhatsappModelMapper mapper;

    @Override
    public void send(MessageRequest messageRequest) throws NumberParseException {
        messageRequest.setPhoneNumber(Sender.phoneNumberFormat(messageRequest.getPhoneNumber()));
        if(Sender.isPhoneNumberValid(messageRequest.getPhoneNumber())){
            WhatsappRequest whatsappRequest = mapper.map(messageRequest);
            Message message = Message.creator(
                            new com.twilio.type.PhoneNumber("whatsapp:"+whatsappRequest.getPhoneNumber()),
                            new com.twilio.type.PhoneNumber("whatsapp:"+twilioConfiguration.getWhatsappTrialNumber()),
                            whatsappRequest.getMessage())
                    .create();
        } else {
            throw new IllegalArgumentException("Invalid Phone Number for Nigeria");
        }

    }
}
