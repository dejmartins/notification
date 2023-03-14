package africa.semicolon.notification.email;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Setter
@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Email {

  @Id private String id;
  @NotBlank private String body;
  private String from;
  private int retryLimit;
  @NotBlank private String subject;
  private String reference;
  private EmailStatus status;
  @jakarta.validation.constraints.Email private String emailAddress;
}
