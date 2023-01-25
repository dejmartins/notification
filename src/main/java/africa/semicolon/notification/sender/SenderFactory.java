package africa.semicolon.notification.sender;

import africa.semicolon.notification.config.twilio.TwilioConfiguration;
import africa.semicolon.notification.email.EmailService;
import africa.semicolon.notification.sms.movider.MoviderSmsSender;
import africa.semicolon.notification.utils.Sender;
import africa.semicolon.notification.whatsapp.mapper.WhatsappModelMapper;
import africa.semicolon.notification.whatsapp.twilio.WhatsappMessageSender;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class SenderFactory {

    @Bean
    public static Sender moviderSender(){
        return new MoviderSmsSender();
    }

    @Bean
    public static Sender emailSender(){
        return new EmailService();
    }

    @Bean
    public static Sender whatsappSender(){
        return new WhatsappMessageSender(new WhatsappModelMapper());
    }

    static Map<String, Sender> senderMap = new HashMap<>();
    static {
        senderMap.put("sms", moviderSender());
        senderMap.put("email", emailSender());
        senderMap.put("whatsapp", whatsappSender());
    }

    public static Optional<Sender> getSender(String type){
        return Optional.ofNullable(senderMap.get(type.toLowerCase()));
    }
}
