package africa.semicolon.notification.auth;

import africa.semicolon.notification.auth.config.JwtService;
import africa.semicolon.notification.auth.dtos.requests.AuthenticationRequest;
import africa.semicolon.notification.auth.dtos.requests.RegisterRequest;
import africa.semicolon.notification.auth.dtos.responses.AuthenticationResponse;
import africa.semicolon.notification.sender.Role;
import africa.semicolon.notification.sender.Sender;
import africa.semicolon.notification.sender.SenderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final SenderRepository senderRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
    var sender =
        Sender.builder()
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .emailAddress(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.USER)
            .build();
    senderRepository.save(sender);
    var jwtToken = jwtService.generateToken(sender);
    return AuthenticationResponse.builder().token(jwtToken).build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    var sender = senderRepository.findByEmailAddressIgnoreCase(request.getEmail()).orElseThrow();
    var jwtToken = jwtService.generateToken(sender);
    return AuthenticationResponse.builder().token(jwtToken).build();
  }
}
