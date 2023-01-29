package africa.semicolon.notification.email.mapper;

import africa.semicolon.notification.email.Email;
import africa.semicolon.notification.dtos.requests.MessageRequest;
import africa.semicolon.notification.email.EmailStatus;
import org.springframework.stereotype.Component;

@Component
public class EmailModelMapper {

    public Email map(MessageRequest messageRequest){
        Email email = new Email();
        email.setSubject(messageRequest.getSubject());
        email.setBody(messageRequest.getMessage());
        email.setStatus(EmailStatus.PENDING);
        email.setReference(messageRequest.getReference());
        email.setEmailAddress(messageRequest.getEmailAddress());
        return email;
    }

    public MessageRequest map(Email email){
        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setMessage(email.getBody());
        messageRequest.setSubject(email.getSubject());
        messageRequest.setReference(email.getReference());
        messageRequest.setEmailAddress(email.getEmailAddress());
        return messageRequest;
    }
}
