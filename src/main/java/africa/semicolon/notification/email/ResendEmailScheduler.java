package africa.semicolon.notification.email;

import africa.semicolon.notification.email.mapper.EmailModelMapper;
import africa.semicolon.notification.email.providers.MailDevEmailSender;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class ResendEmailScheduler {

    private final EmailService emailService;
    private final MailDevEmailSender emailSender;
    private final EmailModelMapper mapper;

    @Scheduled(fixedDelay = 3000, initialDelay = 1000) /*Fixed delay: Five minutes*/
    public void resendEmail() throws MessagingException {
        List<Email> emails = emailSender.findPendingEmails();
        for (Email email : emails){
            if(email.getRetryLimit() < 5){
                emailSender.send(mapper.map(email));
            } else {
                email.setStatus(EmailStatus.UNSENT);
                emailService.save(email);
                throw new IllegalStateException("Email not sent");
            }
        }
    }
}
