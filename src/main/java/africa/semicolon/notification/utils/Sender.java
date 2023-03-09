package africa.semicolon.notification.utils;

import africa.semicolon.notification.dtos.requests.MessageRequest;
import africa.semicolon.notification.exceptions.InvalidPhoneNumberException;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.mashape.unirest.http.exceptions.UnirestException;
import jakarta.mail.MessagingException;
import java.io.IOException;

public interface Sender {

  void send(MessageRequest messageRequest)
      throws MessagingException, IOException, NumberParseException, UnirestException;

  static boolean isPhoneNumberValid(String phoneNumber) {
    PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
    PhoneNumber nigerianNumberProto;

    try {
      nigerianNumberProto = phoneUtil.parse(phoneNumber, "NG");
    } catch (NumberParseException e) {
      throw new InvalidPhoneNumberException("Invalid Phone Number for Nigeria");
    }

    return phoneUtil.isValidNumberForRegion(nigerianNumberProto, "NG");
  }
  ;

  static String phoneNumberFormat(String phoneNumber) throws NumberParseException {
    PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
    PhoneNumber nigerianNumberProto = phoneUtil.parse(phoneNumber, "NG");
    return phoneUtil.format(nigerianNumberProto, PhoneNumberUtil.PhoneNumberFormat.E164);
  }
}
