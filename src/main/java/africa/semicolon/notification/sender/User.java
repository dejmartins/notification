package africa.semicolon.notification.sender;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String id;
    @Email
    private String email;
    @Size(min = 11, max = 14)
    private String phoneNumber;
    @NotBlank
    @Size(min = 8, max = 20)
    private String password;
}
