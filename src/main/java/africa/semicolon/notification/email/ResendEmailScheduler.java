package africa.semicolon.notification.email;

import africa.semicolon.notification.email.mapper.ModelMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@AllArgsConstructor
@RequiredArgsConstructor
public class ResendEmailScheduler {

    @Autowired
    private EmailService emailService;
    private ModelMapper mapper;

    @Scheduled(fixedRate = 50000)
    public void resendEmail() {
        Iterable<ScheduledEmail> emailRequests = emailService.findUnsentEmails();
        for (ScheduledEmail request : emailRequests){
            emailService.send(mapper.map(request));
        }
    }

}
