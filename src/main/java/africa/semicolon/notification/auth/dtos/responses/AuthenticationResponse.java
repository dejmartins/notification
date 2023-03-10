package africa.semicolon.notification.auth.dtos.responses;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthenticationResponse {

  private String token;
}
