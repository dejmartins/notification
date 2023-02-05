package africa.semicolon.notification.dtos.requests;

import africa.semicolon.notification.utils.SendType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class MessageRequest {

    private String type;
    private String from;
    private String message;
    private String subject;
    private String reference;
    private String phoneNumber;
    private String emailAddress;

}
