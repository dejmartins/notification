package africa.semicolon.notification.email;

import africa.semicolon.notification.email.mapper.EmailModelMapper;
import africa.semicolon.notification.email.providers.MailDevEmailSender;
import jakarta.mail.MessagingException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "fixed")
public class ResendEmailScheduler {

  private final MailDevEmailSender emailSender;
  private final EmailModelMapper mapper;
  private final EmailUtil emailUtil;

  @Scheduled(fixedDelayString = "${fixed.in.milliseconds}", initialDelay = 1000) /*Fixed delay: Five minutes*/
  public void resendEmail() throws MessagingException {
    List<Email> emails = emailSender.findPendingEmails();
    for (Email email : emails) {
      if (email.getRetryLimit() < 5) {
        emailSender.send(mapper.map(email));
      } else {
        email.setStatus(EmailStatus.UNSENT);
        emailUtil.save(email);
        throw new IllegalStateException("Email not sent");
      }
    }
  }
}
