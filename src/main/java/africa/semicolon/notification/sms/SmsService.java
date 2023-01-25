package africa.semicolon.notification.sms;

import africa.semicolon.notification.sms.mapper.ModelMapper;
import africa.semicolon.notification.sms.movider.MoviderSmsSender;
import africa.semicolon.notification.utils.Sender;
import africa.semicolon.notification.utils.dtos.requests.MessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SmsService {

    private final Sender smsSender;
    private ModelMapper mapper;

    @Autowired
    public SmsService(@Qualifier("Movider") MoviderSmsSender smsSender) {
        this.smsSender = smsSender;
    }

    public void sendSms(MessageRequest messageRequest) throws IOException {
        smsSender.send(messageRequest);
    }

}
