package africa.semicolon.notification.user;

import africa.semicolon.notification.sms.SmsRequest;
import africa.semicolon.notification.sms.SmsService;
import africa.semicolon.notification.user.dtos.requests.RegistrationRequest;
import africa.semicolon.notification.user.dtos.responses.ApiResponse;
import africa.semicolon.notification.utils.dtos.requests.MessageRequest;
import africa.semicolon.notification.whatsapp.WhatsappService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.ZonedDateTime;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final SmsService smsService;
    private final WhatsappService whatsappService;

    @GetMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest registrationRequest) throws MessagingException, IOException {

        ApiResponse apiResponse = ApiResponse.builder()
                .statusCode(HttpStatus.ACCEPTED)
                .data(userService.register(registrationRequest))
                .timeStamp(ZonedDateTime.now())
                .isSuccessful(true)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.ACCEPTED);
    }

    @PostMapping("/sendSms")
    public void sendSms(@Valid @RequestBody MessageRequest smsRequest) throws IOException {
        smsService.sendSms(smsRequest);
    }

    @PostMapping("/sendWha")
    public void sendWhatsappMessage(@Valid @RequestBody MessageRequest smsRequest) throws IOException {
        whatsappService.send(smsRequest);
    }
}
