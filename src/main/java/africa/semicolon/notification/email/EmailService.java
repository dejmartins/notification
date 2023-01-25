package africa.semicolon.notification.email;

import africa.semicolon.notification.email.mapper.ModelMapper;
import africa.semicolon.notification.utils.Sender;
import africa.semicolon.notification.dtos.requests.MessageRequest;
import africa.semicolon.notification.dtos.responses.SendResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class EmailService implements Sender {

    @Autowired
    private EmailRepository emailRepository;
    private JavaMailSender javaMailSender;
    private ModelMapper mapper;

    @Async
    @Override
    public CompletableFuture<SendResponse> send(MessageRequest messageRequest) {
        ScheduledEmail scheduledEmail = mapper.map(messageRequest);
        try {
            MimeMessage mailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mailMessage, "utf-8");
            mimeMessageHelper.setSubject(scheduledEmail.getSubject());
            mimeMessageHelper.setTo(scheduledEmail.getEmailAddress());
            mimeMessageHelper.setFrom("dej@gmail.com");
            mimeMessageHelper.setText(scheduledEmail.getBody(), true);
            javaMailSender.send(mailMessage);
            scheduledEmail.setHasSent(true);

            return CompletableFuture.completedFuture(new SendResponse(
                    HttpStatus.OK.value(),
                    "Email sent",
                    true
            ));

        } catch (MessagingException | MailException e) {
            emailRepository.save(scheduledEmail);
            throw new RuntimeException(e);
        }
    }

    public Iterable<ScheduledEmail> findUnsentEmails(){
        return emailRepository.findUnsentEmails();
    }
}
