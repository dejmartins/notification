package africa.semicolon.notification.sms;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SmsRequest {

  @NotBlank private String phoneNumber;
  @NotBlank private String message;
}
