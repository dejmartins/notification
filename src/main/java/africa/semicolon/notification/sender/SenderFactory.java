package africa.semicolon.notification.sender;

import africa.semicolon.notification.email.EmailService;
import africa.semicolon.notification.sms.SmsService;
import africa.semicolon.notification.utils.SendType;
import africa.semicolon.notification.utils.Sender;
import africa.semicolon.notification.whatsapp.WhatsappService;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SenderFactory {

  private final EmailService emailService;
  private final SmsService smsService;
  private final WhatsappService whatsappService;
  private Map<SendType, Sender> senderMap;

  @PostConstruct
  public void initializeServices() {
    this.senderMap = new HashMap<>();
    senderMap.put(SendType.EMAIL, emailService);
    senderMap.put(SendType.SMS, smsService);
    senderMap.put(SendType.WHATSAPP, whatsappService);
  }

  public Sender getSender(SendType type) {
    return senderMap.get(type);
  }
}
