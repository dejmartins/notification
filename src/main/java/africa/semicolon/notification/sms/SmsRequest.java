package africa.semicolon.notification.sms;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SmsRequest {

    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String message;
}
