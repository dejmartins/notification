package africa.semicolon.notification.config.email;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@ConfigurationProperties(prefix = "mailgun")
public class MailGunConfiguration {

    private final String apiKey;
    private final String domain;

    @Autowired
    public MailGunConfiguration(@Value("${mailgun.api_key}") String apiKey, @Value("${mailgun.domain}")String domain) {
        this.apiKey = apiKey;
        this.domain = domain;
    }
}
