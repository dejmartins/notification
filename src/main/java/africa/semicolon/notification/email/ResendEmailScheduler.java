package africa.semicolon.notification.email;

import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@AllArgsConstructor
public class ResendEmailScheduler {

    private final EmailService emailService;

    @Scheduled(fixedRate = 50000)
    public void resendEmail() throws MessagingException {
        Iterable<ScheduledEmailRequest> emailRequests = emailService.findUnsentEmails();
        for (ScheduledEmailRequest request : emailRequests){
            emailService.send(request);
        }
    }

}
