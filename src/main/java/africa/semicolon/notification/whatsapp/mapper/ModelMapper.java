package africa.semicolon.notification.whatsapp.mapper;

import africa.semicolon.notification.utils.dtos.requests.MessageRequest;
import africa.semicolon.notification.whatsapp.WhatsappRequest;

public class ModelMapper {
    public WhatsappRequest map(MessageRequest messageRequest) {
        WhatsappRequest whatsappRequest = new WhatsappRequest();
        whatsappRequest.setMessage(messageRequest.getMessage());
        whatsappRequest.setPhoneNumber(messageRequest.getPhoneNumber());
        return whatsappRequest;
    }
}
