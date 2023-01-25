package africa.semicolon.notification.utils;


import africa.semicolon.notification.dtos.requests.MessageRequest;
import africa.semicolon.notification.dtos.responses.SendResponse;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public interface Sender {

    CompletableFuture<SendResponse> send(MessageRequest messageRequest) throws IOException;
}
