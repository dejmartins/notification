package africa.semicolon.notification.utils;


import africa.semicolon.notification.dtos.requests.MessageRequest;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import jakarta.mail.MessagingException;

import java.io.IOException;


public interface Sender {

    void send(MessageRequest messageRequest) throws MessagingException, IOException, NumberParseException;

    static boolean isPhoneNumberValid(String phoneNumber) {
        System.out.println(phoneNumber);

        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        PhoneNumber nigerianNumberProto;

        try{
            nigerianNumberProto = phoneUtil.parse(phoneNumber, "NG");
        } catch (NumberParseException e) {
            throw new RuntimeException("Invalid Phone Number for Nigeria");
        }

        return phoneUtil.isValidNumberForRegion(nigerianNumberProto, "NG");
    };

    static String phoneNumberFormat(String phoneNumber) throws NumberParseException {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        PhoneNumber nigerianNumberProto = phoneUtil.parse(phoneNumber, "NG");
        return phoneUtil.format(nigerianNumberProto, PhoneNumberUtil.PhoneNumberFormat.E164);
    }


}
