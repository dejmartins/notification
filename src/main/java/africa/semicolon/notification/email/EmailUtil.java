package africa.semicolon.notification.email;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmailUtil {

    private final EmailRepository emailRepository;

    public List<Email> findPendingEmails(){
        return emailRepository.findPendingEmails();
    }

    public void save(Email email) {
        Optional<Email> foundEmail = emailRepository.findByReference(email.getReference());
        if (foundEmail.isPresent()){
            foundEmail.get().setStatus(email.getStatus());
            foundEmail.get().setRetryLimit(foundEmail.get().getRetryLimit() + 1);
            emailRepository.save(foundEmail.get());
        } else emailRepository.save(email);
    }

    public void test(){
        //just testing
    }
}
