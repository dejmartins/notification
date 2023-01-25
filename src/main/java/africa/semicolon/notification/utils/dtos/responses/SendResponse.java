package africa.semicolon.notification.utils.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SendResponse {

    private int httpStatus;
    private Object data;
    private boolean isSuccessful;
}
