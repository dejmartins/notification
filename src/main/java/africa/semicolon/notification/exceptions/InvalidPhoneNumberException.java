package africa.semicolon.notification.exceptions;

public class InvalidPhoneNumberException extends RuntimeException{

    public InvalidPhoneNumberException(String message){
        super(message);
    }
}
