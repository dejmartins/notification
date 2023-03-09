package africa.semicolon.notification.whatsapp.mapper;

import africa.semicolon.notification.dtos.requests.MessageRequest;
import africa.semicolon.notification.whatsapp.WhatsappRequest;
import org.springframework.stereotype.Component;

@Component
public class WhatsappModelMapper {

  public WhatsappRequest map(MessageRequest messageRequest) {
    WhatsappRequest whatsappRequest = new WhatsappRequest();
    whatsappRequest.setMessage(messageRequest.getMessage());
    whatsappRequest.setPhoneNumber(messageRequest.getPhoneNumber());
    return whatsappRequest;
  }
}
