package africa.semicolon.notification.email.providers;

import africa.semicolon.notification.dtos.requests.MessageRequest;
import africa.semicolon.notification.email.Email;
import africa.semicolon.notification.email.EmailRepository;
import africa.semicolon.notification.email.EmailService;
import africa.semicolon.notification.email.mapper.ModelMapper;
import africa.semicolon.notification.utils.SendType;
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
    private final ModelMapper mapper;

    public SendType getType() {
        return SendType.EMAIL;
    }

    @Async
    public void send(MessageRequest messageRequest) throws MessagingException {
        Email email = mapper.map(messageRequest);
        try{
            MimeMessage mailMessage = javaMailSender.createMimeMessage();
            System.out.println(javaMailSender);
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mailMessage, "utf-8");
            mimeMessageHelper.setSubject(email.getSubject());
            mimeMessageHelper.setTo(email.getEmailAddress());
            mimeMessageHelper.setFrom("dej@gmail.com");
            mimeMessageHelper.setText(email.getBody(), true);
            save(email);
            javaMailSender.send(mailMessage);
            email.setHasSent(true);
        } catch (MessagingException | MailException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Email> findUnsentEmails(){
        return emailRepository.findUnsentEmails();
    }

    @Override
    public void save(Email email) {
        Optional<Email> foundEmail = emailRepository.findByEmailAddressIgnoreCase(email.getEmailAddress());
        if (foundEmail.isPresent()){
            foundEmail.get().setTrialLimit(foundEmail.get().getTrialLimit() + 1);
            emailRepository.save(foundEmail.get());
        } else emailRepository.save(email);
    }

}
