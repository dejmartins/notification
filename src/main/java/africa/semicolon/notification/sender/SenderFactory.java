package africa.semicolon.notification.sender;

import africa.semicolon.notification.config.movider.MoviderConfiguration;
import africa.semicolon.notification.config.twilio.TwilioConfiguration;
import africa.semicolon.notification.email.EmailService;
import africa.semicolon.notification.sms.SmsService;
import africa.semicolon.notification.sms.mapper.SmsModelMapper;
import africa.semicolon.notification.sms.movider.MoviderSmsSender;
import africa.semicolon.notification.utils.Sender;
import africa.semicolon.notification.whatsapp.WhatsappService;
import africa.semicolon.notification.whatsapp.mapper.WhatsappModelMapper;
import africa.semicolon.notification.whatsapp.twilio.WhatsappMessageSender;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class SenderFactory {


    @Bean
    public static Sender emailSender(){
        return new EmailService();
    }

    @Bean
    public static Sender smsSender(){
        return new SmsService(new MoviderSmsSender(new SmsModelMapper()));
    }

    @Bean
    public static Sender whatsappSender(){
        return new WhatsappService(new WhatsappMessageSender(new WhatsappModelMapper()));
    }

    static Map<String, Sender> senderMap = new HashMap<>();
    static {
        senderMap.put("email", emailSender());
        senderMap.put("sms", smsSender());
        senderMap.put("whatsapp", whatsappSender());
    }

    public static Optional<Sender> getSender(String type){
        return Optional.ofNullable(senderMap.get(type.toLowerCase()));
    }
}
