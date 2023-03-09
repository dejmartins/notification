package africa.semicolon.notification.sender;

import africa.semicolon.notification.dtos.requests.MessageRequest;
import africa.semicolon.notification.dtos.responses.ApiResponse;
import africa.semicolon.notification.exceptions.InvalidSendTypeException;
import com.google.i18n.phonenumbers.NumberParseException;
import com.mashape.unirest.http.exceptions.UnirestException;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import java.io.IOException;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/notification")
@AllArgsConstructor
public class SendController {

  private final SenderService senderService;

  @PostMapping("/send")
  public ResponseEntity<?> sendSms(@Valid @RequestBody MessageRequest request)
      throws MessagingException, IOException, NumberParseException, InvalidSendTypeException,
          UnirestException {
    senderService.send(request);
    ApiResponse apiResponse =
        ApiResponse.builder()
            .data(ResponseEntity.ok("Pending"))
            .timeStamp(ZonedDateTime.now())
            .isSuccessful(true)
            .build();

    return new ResponseEntity<>(apiResponse, HttpStatus.ACCEPTED);
  }
}
