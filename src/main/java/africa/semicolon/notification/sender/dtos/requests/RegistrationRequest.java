package africa.semicolon.notification.sender.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegistrationRequest {

    private String email;
    private String phoneNumber;
    private String password;
}
