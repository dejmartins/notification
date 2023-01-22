package africa.semicolon.notification.sms.termii;

import africa.semicolon.notification.sms.SmsRequest;
import africa.semicolon.notification.sms.SmsSender;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.springframework.stereotype.Service;

@Service("Termii")
public class TermiiSmsSender implements SmsSender {
    @Override
    public void send(SmsRequest smsRequest) {
//        Unirest.(0, 0);

        Unirest.post(" https://api.ng.termii.com/api/sms/number/send")
                .header("Content-Type", "application/json")
                .body("{\r\n \"to\": \"2348088893600\",\r\n  \"sms\": \"Hi there, testing Termii \",\r\n   \"api_key\": \"API_KEY\"\r\n    }");

        System.out.println("Donee");
    }
}
