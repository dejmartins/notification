package africa.semicolon.notification.sender;

import africa.semicolon.notification.dtos.requests.MessageRequest;
import africa.semicolon.notification.dtos.responses.ApiResponse;
import africa.semicolon.notification.exceptions.InvalidSendTypeException;
import com.google.i18n.phonenumbers.NumberParseException;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.ZonedDateTime;

@RestController
@RequestMapping("api/v1/notification")
@AllArgsConstructor
public class SendController {

    private final SenderService senderService;

    @PostMapping("/send")
    public ResponseEntity<?> sendSms(@Valid @RequestBody MessageRequest request) throws MessagingException,
            IOException,
            NumberParseException,
            InvalidSendTypeException{
        senderService.send(request);
        ApiResponse apiResponse = ApiResponse.builder()
                .data(ResponseEntity.ok("Sent"))
                .timeStamp(ZonedDateTime.now())
                .isSuccessful(true)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.ACCEPTED);
    }
}
