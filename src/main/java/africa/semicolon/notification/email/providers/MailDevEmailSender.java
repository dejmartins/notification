package africa.semicolon.notification.email.providers;

import africa.semicolon.notification.dtos.requests.MessageRequest;
import africa.semicolon.notification.email.Email;
import africa.semicolon.notification.email.EmailRepository;
import africa.semicolon.notification.email.EmailService;
import africa.semicolon.notification.email.EmailStatus;
import africa.semicolon.notification.email.mapper.EmailModelMapper;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class MailDevEmailSender implements EmailService {
    private final EmailRepository emailRepository;
    private final JavaMailSender javaMailSender;
    private final EmailModelMapper mapper;

    @Async
    public void send(MessageRequest messageRequest) throws MessagingException {
        Email email = mapper.map(messageRequest);
        try{
            MimeMessage mailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mailMessage, "utf-8");
            mimeMessageHelper.setSubject(email.getSubject());
            mimeMessageHelper.setTo(email.getEmailAddress());
            mimeMessageHelper.setFrom(messageRequest.getFrom());
            mimeMessageHelper.setText(email.getBody(), true);
            javaMailSender.send(mailMessage);
            email.setStatus(EmailStatus.SENT);
            save(email);
        } catch (MessagingException | MailException e) {
            save(email);
            log.error(e.getMessage());
            throw new MessagingException(e.getMessage() + "- EMAIL NOT SENT");
        }
    }

    public List<Email> findPendingEmails(){
        return emailRepository.findPendingEmails();
    }

    @Override
    public void save(Email email) {
        Optional<Email> foundEmail = emailRepository.findByReference(email.getReference());
        if (foundEmail.isPresent()){
            foundEmail.get().setStatus(email.getStatus());
            foundEmail.get().setRetryLimit(foundEmail.get().getRetryLimit() + 1);
            emailRepository.save(foundEmail.get());
        } else emailRepository.save(email);
    }

}
