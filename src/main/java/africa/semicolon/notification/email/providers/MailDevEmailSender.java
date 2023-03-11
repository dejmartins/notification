package africa.semicolon.notification.email.providers;

import africa.semicolon.notification.dtos.requests.MessageRequest;
import africa.semicolon.notification.email.*;
import africa.semicolon.notification.email.mapper.EmailModelMapper;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Primary
@Component
public class MailDevEmailSender extends EmailUtil implements EmailService {
  private final JavaMailSender javaMailSender;
  private final EmailModelMapper mapper;

  @Autowired
  public MailDevEmailSender(
      EmailRepository emailRepository,
      JavaMailSender javaMailSender,
      EmailModelMapper emailModelMapper) {
    super(emailRepository);
    this.javaMailSender = javaMailSender;
    this.mapper = emailModelMapper;
  }

  @Async
  public void send(MessageRequest messageRequest) throws MessagingException {
    Email email = mapper.map(messageRequest);
    try {
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
}
