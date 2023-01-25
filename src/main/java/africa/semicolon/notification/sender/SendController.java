package africa.semicolon.notification.sender;

import africa.semicolon.notification.sender.dtos.responses.ApiResponse;
import africa.semicolon.notification.dtos.requests.MessageRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.ZonedDateTime;

@RestController
@RequestMapping("api/v1/notification")
@AllArgsConstructor
public class SendController {

    private final SenderService userService;

    @PostMapping("/send")
    public ResponseEntity<?> sendSms(@Valid @RequestBody MessageRequest request) throws IOException {
        ApiResponse apiResponse = ApiResponse.builder()
                .data(userService.send(request))
                .timeStamp(ZonedDateTime.now())
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.ACCEPTED);
    }
}
