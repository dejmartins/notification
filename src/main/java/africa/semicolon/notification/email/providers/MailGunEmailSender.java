package africa.semicolon.notification.email.providers;

import africa.semicolon.notification.dtos.requests.MessageRequest;
import africa.semicolon.notification.email.EmailRepository;
import africa.semicolon.notification.email.EmailService;
import africa.semicolon.notification.email.EmailUtil;
import com.google.i18n.phonenumbers.NumberParseException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.squareup.okhttp.*;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import okio.ByteString;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@Profile(value = "dev")
public class MailGunEmailSender extends EmailUtil implements EmailService  {

    @Autowired
    public MailGunEmailSender(EmailRepository emailRepository) {
        super(emailRepository);
    }

    @Override
    public void send(MessageRequest messageRequest) throws MessagingException, IOException, NumberParseException, UnirestException {

        HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + "sandbox43f85dd1abd4403c853d8d920230a4c4.mailgun.org" + "/messages")
			.basicAuth("api", "159c07b5248aa818bb0bff5833a64617-1b3a03f6-f0fac24d")
                .queryString("from", "boyo@learnspace.africa")
                .queryString("to", "tbthecoder.dev@gmail.com")
                .queryString("subject", "hello")
                .queryString("text", "testing")
                .asJson();


        log.info(request.getBody().toString());
    }
}
