package africa.semicolon.notification.config.twilio;

import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioInitializer {

  @Autowired
  public TwilioInitializer(TwilioConfiguration twilioConfiguration) {
    String ACCOUNT_SID = twilioConfiguration.getAccountSid();
    String AUTH_TOKEN = twilioConfiguration.getAuthToken();
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
  }
}
