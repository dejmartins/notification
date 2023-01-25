package africa.semicolon.notification.dtos.requests;

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
    private String phoneNumber;
    private String emailAddress;

}
