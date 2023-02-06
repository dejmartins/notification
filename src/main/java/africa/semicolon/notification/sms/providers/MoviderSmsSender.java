package africa.semicolon.notification.sms.providers;

import africa.semicolon.notification.config.movider.MoviderConfiguration;
import africa.semicolon.notification.dtos.requests.MessageRequest;
import africa.semicolon.notification.exceptions.InvalidPhoneNumberException;
import africa.semicolon.notification.sms.SmsRequest;
import africa.semicolon.notification.sms.SmsService;
import africa.semicolon.notification.sms.mapper.SmsModelMapper;
import africa.semicolon.notification.utils.Sender;
import com.google.i18n.phonenumbers.NumberParseException;
import com.squareup.okhttp.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Primary
@Component
@RequiredArgsConstructor
public class MoviderSmsSender implements SmsService {
    private final SmsModelMapper mapper;
    private final MoviderConfiguration moviderConfiguration;


    @Override
    public void send(MessageRequest messageRequest) throws IOException, NumberParseException {
        messageRequest.setPhoneNumber(Sender.phoneNumberFormat(messageRequest.getPhoneNumber()));
        SmsRequest smsRequest = mapper.map(messageRequest);

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

        if(Sender.isPhoneNumberValid(smsRequest.getPhoneNumber())){
            Response response = client.newCall(request).execute();
        } else {
            throw new InvalidPhoneNumberException("Invalid Phone Number for Nigeria");
        }
    }



}
