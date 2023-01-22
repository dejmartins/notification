package africa.semicolon.notification.user;

import africa.semicolon.notification.user.dtos.requests.RegistrationRequest;
import africa.semicolon.notification.user.dtos.responses.ApiResponse;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest registrationRequest) throws MessagingException {

        ApiResponse apiResponse = ApiResponse.builder()
                .statusCode(HttpStatus.ACCEPTED)
                .data(userService.register(registrationRequest))
                .timeStamp(ZonedDateTime.now())
                .isSuccessful(true)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.ACCEPTED);
    }
}
