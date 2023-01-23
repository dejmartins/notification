package africa.semicolon.notification.sms;

import africa.semicolon.notification.sms.movider.MoviderSmsSender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SmsService {

    private final SmsSender smsSender;

    @Autowired
    public SmsService(@Qualifier("Movider") MoviderSmsSender smsSender) {
        this.smsSender = smsSender;
    }

    public void sendSms(SmsRequest smsRequest) throws IOException {
        smsSender.send(smsRequest);
    }

}
