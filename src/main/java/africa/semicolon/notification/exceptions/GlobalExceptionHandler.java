package africa.semicolon.notification.exceptions;

import africa.semicolon.notification.dtos.responses.ApiResponse;
import com.google.i18n.phonenumbers.NumberParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler( {InvalidSendTypeException.class} )
    public ResponseEntity<?> invalidSendType(InvalidSendTypeException exception){
        ApiResponse response = new ApiResponse(
                ZonedDateTime.now(),
                exception.getMessage(),
                false
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler( {NumberParseException.class} )
    public ResponseEntity<?> invalidPhoneNumber(NumberParseException exception){
        ApiResponse response = new ApiResponse(
                ZonedDateTime.now(),
                exception.getMessage(),
                false
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler( {InvalidPhoneNumberException.class} )
    public ResponseEntity<?> illegalArgument(InvalidPhoneNumberException exception){
        ApiResponse response = new ApiResponse(
                ZonedDateTime.now(),
                exception.getMessage(),
                false
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
