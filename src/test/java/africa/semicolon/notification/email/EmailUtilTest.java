package africa.semicolon.notification.email;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {EmailUtil.class})
@ExtendWith(SpringExtension.class)
class EmailUtilTest {

    @MockBean
    private EmailRepository emailRepository;
    @Autowired
    private EmailUtil emailUtil;

    @Test
    public void test_EmailPersists() {
        Email email = Email.builder()
                .from("joe@gmail.com")
                .emailAddress("dej@gmail.com")
                .subject("Hello from the Dreaming Spires")
                .body("Hello people")
                .reference("05a045be-c295-4820-b9a2-222bb863b0fd")
                .build();
        when(emailRepository.findByReference("05a045be-c295-4820-b9a2-222bb863b0fd")).thenReturn(Optional.empty());
        emailUtil.save(email);

        when(emailRepository.findByReference("05a045be-c295-4820-b9a2-222bb863b0fd")).thenReturn(Optional.of(email));
        String foundEmailReference = emailRepository.findByReference("05a045be-c295-4820-b9a2-222bb863b0fd").get().getReference();
        assertEquals(foundEmailReference, email.getReference());
    }

}