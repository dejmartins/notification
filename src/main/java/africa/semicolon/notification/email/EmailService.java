package africa.semicolon.notification.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

    private JavaMailSender javaMailSender;
    private EmailRepository emailRepository;

    @Async
    @Override
    public void send(ScheduledEmailRequest scheduleEmailRequest) throws MessagingException {
        try{
            MimeMessage mailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mailMessage, "utf-8");
            mimeMessageHelper.setSubject(scheduleEmailRequest.getSubject());
            mimeMessageHelper.setTo(scheduleEmailRequest.getEmailAddress());
            mimeMessageHelper.setFrom("dej@gmail.com");
            mimeMessageHelper.setText(scheduleEmailRequest.getBody(), true);
            javaMailSender.send(mailMessage);
            scheduleEmailRequest.setHasSent(true);
        } catch (MessagingException | MailException e) {
            emailRepository.save(scheduleEmailRequest);
            throw new RuntimeException(e);
        }
    }

    public Iterable<ScheduledEmailRequest> findUnsentEmails(){
        return emailRepository.findUnsentEmails();
    }
}
