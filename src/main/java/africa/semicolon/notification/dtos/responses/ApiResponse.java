package africa.semicolon.notification.dtos.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class ApiResponse {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private ZonedDateTime timeStamp;
    private Object data;
    private boolean isSuccessful;
}
