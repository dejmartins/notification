package africa.semicolon.notification.email;

import africa.semicolon.notification.utils.Sender;

import java.util.List;

public interface EmailService extends Sender {

    List<Email> findUnsentEmails();
    void save(Email email);

}
