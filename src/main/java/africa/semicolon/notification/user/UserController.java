package africa.semicolon.notification.user;

import africa.semicolon.notification.sms.SmsRequest;
import africa.semicolon.notification.sms.SmsService;
import africa.semicolon.notification.user.dtos.requests.RegistrationRequest;
import africa.semicolon.notification.user.dtos.responses.ApiResponse;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final SmsService smsService;

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

    @PostMapping("/sendSms")
    public void sendSms(@Valid @RequestBody SmsRequest smsRequest) {
        smsService.sendSms(smsRequest);
    }
}
