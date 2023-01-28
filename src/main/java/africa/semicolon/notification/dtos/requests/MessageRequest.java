package africa.semicolon.notification.dtos.requests;

import africa.semicolon.notification.utils.SendType;
import lombok.*;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class MessageRequest {

    private SendType type;
    private String from;
    private String message;
    private String subject;
    private String phoneNumber;
    private String emailAddress;

}
