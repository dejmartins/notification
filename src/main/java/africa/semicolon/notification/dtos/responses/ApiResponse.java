package africa.semicolon.notification.dtos.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
