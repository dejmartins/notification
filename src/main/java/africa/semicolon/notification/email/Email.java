package africa.semicolon.notification.email;

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
public class Email {

    @Id
    private String id;
    private String body;
    private int retryLimit;
    @NotBlank
    private String subject;
    private String reference;
    private EmailStatus status;
    @jakarta.validation.constraints.Email
    private String emailAddress;
}
