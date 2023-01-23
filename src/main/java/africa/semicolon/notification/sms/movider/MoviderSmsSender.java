package africa.semicolon.notification.sms.movider;

import africa.semicolon.notification.sms.SmsRequest;
import africa.semicolon.notification.sms.SmsSender;
import com.squareup.okhttp.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service("Movider")
@AllArgsConstructor
public class MoviderSmsSender implements SmsSender {

    private final MoviderConfiguration moviderConfiguration;
    @Override
    public void send(SmsRequest smsRequest) throws IOException {

        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");

        RequestBody body = RequestBody.create(mediaType,
                "api_key=" + moviderConfiguration.getApiKey()
                        + "&api_secret=" + moviderConfiguration.getApiSecret()
                        + "&from=" + moviderConfiguration.getSenderId()
                        + "&to=" + smsRequest.getPhoneNumber()
                        + "&text=" + smsRequest.getMessage());

        Request request = new Request.Builder()
                .url("https://api.movider.co/v1/sms")
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();

        Response response = client.newCall(request).execute();
        log.info(String.valueOf(response));

    }
}
