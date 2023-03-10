package africa.semicolon.notification.config.movider;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "movider")
public class MoviderConfiguration {

  private String apiKey;
  private String apiSecret;
  private String senderId;
}
