package africa.semicolon.notification.email.mapper;

import africa.semicolon.notification.email.ScheduledEmail;
import africa.semicolon.notification.utils.dtos.requests.MessageRequest;

public class ModelMapper {

    public ScheduledEmail map(MessageRequest messageRequest){
        ScheduledEmail scheduledEmail = new ScheduledEmail();
        scheduledEmail.setSubject(messageRequest.getSubject());
        scheduledEmail.setBody(messageRequest.getMessage());
        scheduledEmail.setEmailAddress(messageRequest.getEmailAddress());
        return scheduledEmail;
    }

    public MessageRequest map(ScheduledEmail scheduledEmail){
        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setMessage(scheduledEmail.getBody());
        messageRequest.setSubject(scheduledEmail.getSubject());
        messageRequest.setEmailAddress(scheduledEmail.getEmailAddress());
        return messageRequest;
    }
}
