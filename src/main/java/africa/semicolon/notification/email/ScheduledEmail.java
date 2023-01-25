package africa.semicolon.notification.email;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class ScheduledEmail {

    @Id
    private String id;
    @Email
    private String emailAddress;
    @NotBlank
    private String subject;
    private String body;
    private boolean hasSent;
}
