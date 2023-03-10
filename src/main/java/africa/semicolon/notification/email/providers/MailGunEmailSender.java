package africa.semicolon.notification.email.providers;

import africa.semicolon.notification.config.email.MailGunConfiguration;
import africa.semicolon.notification.dtos.requests.MessageRequest;
import africa.semicolon.notification.email.EmailRepository;
import africa.semicolon.notification.email.EmailService;
import africa.semicolon.notification.email.EmailUtil;
import com.google.i18n.phonenumbers.NumberParseException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import jakarta.mail.MessagingException;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile(value = "dev")
public class MailGunEmailSender extends EmailUtil implements EmailService {

  private final MailGunConfiguration mailGunConfiguration;

  @Autowired
  public MailGunEmailSender(
      EmailRepository emailRepository, MailGunConfiguration mailGunConfiguration) {
    super(emailRepository);
    this.mailGunConfiguration = mailGunConfiguration;
  }

  @Override
  public void send(MessageRequest messageRequest)
      throws MessagingException, IOException, NumberParseException, UnirestException {

    HttpResponse<JsonNode> request =
        Unirest.post("https://api.mailgun.net/v3/" + mailGunConfiguration.getDomain() + "/messages")
            .basicAuth("api", mailGunConfiguration.getApiKey())
            .queryString("from", messageRequest.getFrom())
            .queryString("to", messageRequest.getEmailAddress())
            .queryString("subject", messageRequest.getSubject())
            .queryString("text", messageRequest.getMessage())
            .asJson();

    log.info(request.getBody().toString());
  }
}
