package africa.semicolon.notification.config.twilio;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "twilio")
public class TwilioConfiguration {

  private String accountSid;
  private String authToken;
  private String trialNumber;
  private String whatsappTrialNumber;
}
