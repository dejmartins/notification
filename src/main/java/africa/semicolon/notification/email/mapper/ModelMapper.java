package africa.semicolon.notification.email.mapper;

import africa.semicolon.notification.email.Email;
import africa.semicolon.notification.dtos.requests.MessageRequest;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {

    public Email map(MessageRequest messageRequest){
        Email scheduledEmail = new Email();
        scheduledEmail.setSubject(messageRequest.getSubject());
        scheduledEmail.setBody(messageRequest.getMessage());
        scheduledEmail.setEmailAddress(messageRequest.getEmailAddress());
        return scheduledEmail;
    }

    public MessageRequest map(Email email){
        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setMessage(email.getBody());
        messageRequest.setSubject(email.getSubject());
        messageRequest.setEmailAddress(email.getEmailAddress());
        return messageRequest;
    }
}
