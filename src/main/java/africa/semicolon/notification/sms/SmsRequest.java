package africa.semicolon.notification.sms;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class SmsRequest {

    @NotBlank
    private final String phoneNumber;
    private final String message;
}
