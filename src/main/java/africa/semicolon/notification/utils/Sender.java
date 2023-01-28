package africa.semicolon.notification.utils;


import africa.semicolon.notification.dtos.requests.MessageRequest;
import jakarta.mail.MessagingException;

import java.io.IOException;

public interface Sender {

    SendType getType();

    void send(MessageRequest messageRequest) throws MessagingException, IOException;
}
