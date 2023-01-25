package africa.semicolon.notification.sms.movider;

import africa.semicolon.notification.sms.mapper.SmsModelMapper;
import africa.semicolon.notification.utils.Sender;
import africa.semicolon.notification.config.movider.MoviderConfiguration;
import africa.semicolon.notification.sms.SmsRequest;
import africa.semicolon.notification.dtos.requests.MessageRequest;
import africa.semicolon.notification.dtos.responses.SendResponse;
import com.squareup.okhttp.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service("Movider")
@AllArgsConstructor
@RequiredArgsConstructor
public class MoviderSmsSender implements Sender {

    private MoviderConfiguration moviderConfiguration;
    private SmsModelMapper mapper;

    @Override
    public CompletableFuture<SendResponse> send(MessageRequest messageRequest) throws IOException {
        SmsRequest smsRequest = mapper.map(messageRequest);
        smsRequest.setPhoneNumber(phoneNumberFormat(smsRequest.getPhoneNumber()));

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

        return CompletableFuture.completedFuture(new SendResponse(
                HttpStatus.OK.value(),
                response,
                true
        ));
    }

    private String phoneNumberFormat(String phoneNumber){
        if (phoneNumber.startsWith("+")) {
            phoneNumber = phoneNumber.substring(1);
        }

        if(phoneNumber.startsWith("0")){
            phoneNumber = "234" + phoneNumber.substring(1);
        }

        return phoneNumber;
    }


}
