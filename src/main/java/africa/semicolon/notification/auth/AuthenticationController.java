package africa.semicolon.notification.auth;

import africa.semicolon.notification.auth.dtos.requests.AuthenticationRequest;
import africa.semicolon.notification.auth.dtos.requests.RegisterRequest;
import africa.semicolon.notification.auth.dtos.responses.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
    return ResponseEntity.ok(authenticationService.register(request));
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
    return ResponseEntity.ok(authenticationService.authenticate(request));
  }
}
