package africa.semicolon.notification.sms;

import africa.semicolon.notification.dtos.responses.SendResponse;
import africa.semicolon.notification.sms.mapper.SmsModelMapper;
import africa.semicolon.notification.sms.movider.MoviderSmsSender;
import africa.semicolon.notification.utils.Sender;
import africa.semicolon.notification.dtos.requests.MessageRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class SmsService implements Sender{

    private Sender smsSender;

    @Autowired
    public SmsService(@Qualifier("Movider") MoviderSmsSender smsSender) {
        this.smsSender = smsSender;
    }

    @Override
    public CompletableFuture<SendResponse> send(MessageRequest messageRequest) throws IOException {
        return smsSender.send(messageRequest);
    }
}
