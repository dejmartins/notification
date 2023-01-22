package africa.semicolon.notification.user.dtos.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class ApiResponse {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private ZonedDateTime timeStamp;
    private HttpStatus statusCode;
    private Object data;
    private Boolean isSuccessful;
}
