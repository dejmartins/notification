package africa.semicolon.notification.sender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document
@AllArgsConstructor
@RequiredArgsConstructor
public class Sender {

    @Id
    private String id;
    @NotBlank
    private String lastName;
    @NotBlank
    private String firstName;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String companyName;
    @Email
    private String emailAddress;

}
