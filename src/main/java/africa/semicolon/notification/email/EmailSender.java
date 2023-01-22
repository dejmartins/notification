package africa.semicolon.notification.email;

import jakarta.mail.MessagingException;

public interface EmailSender {

    void send(ScheduledEmailRequest scheduleEmailRequest) throws MessagingException;

    Iterable<ScheduledEmailRequest> findUnsentEmails();
}
