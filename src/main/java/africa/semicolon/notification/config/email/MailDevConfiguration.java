package africa.semicolon.notification.config.email;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Getter
@Setter
@Configuration
@Profile(value = "dev")
@ConfigurationProperties(prefix = "spring.mail")
public class MailDevConfiguration {
    private String host;
    private int port;
    private String username;
    private String password;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("spring.mail.properties.mail.smtp.auth", "*");
        props.put("spring.mail.properties.mail.smtp.ssl.trust", "true");
        props.put("spring.mail.properties.mail.smtp.starttls.enable", "true");
        props.put("spring.mail.properties.mail.smtp.connectiontimeout", "5000");
        props.put("spring.mail.properties.mail.smtp.timeout", "3000");
        props.put("spring.mail.properties.mail.smtp.writetimeout", "5000");

        return mailSender;
    }


}
