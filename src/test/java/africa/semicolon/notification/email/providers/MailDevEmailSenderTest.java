package africa.semicolon.notification.email.providers;

import africa.semicolon.notification.dtos.requests.MessageRequest;
import africa.semicolon.notification.email.Email;
import africa.semicolon.notification.email.EmailRepository;
import africa.semicolon.notification.email.EmailStatus;
import africa.semicolon.notification.email.mapper.EmailModelMapper;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {MailDevEmailSender.class})
@ExtendWith(SpringExtension.class)
class MailDevEmailSenderTest {
    @MockBean
    private EmailModelMapper emailModelMapper;

    @MockBean
    private EmailRepository emailRepository;

    @MockBean
    private JavaMailSender javaMailSender;

    @Autowired
    private MailDevEmailSender mailDevEmailSender;

    /**
     * Method under test: {@link MailDevEmailSender#send(MessageRequest)}
     */
    @Test
    void test_ThrowMessagingExceptions_On_InvalidEmailFormat() {
        Email foundEmail = new Email();
        foundEmail.setBody("Hello World");
        foundEmail.setEmailAddress("Sabo, Yaba");
        foundEmail.setFrom("jerry@gmail.com");
        foundEmail.setId("63e62275029e923494c88962");
        foundEmail.setReference("05a045be-c295-4820-b9a2-222bb863b0fd");
        foundEmail.setRetryLimit(0);
        foundEmail.setStatus(EmailStatus.SENT);
        foundEmail.setSubject("Hello from the Dreaming Spires");

        Optional<Email> result = Optional.of(foundEmail);

        when(emailRepository.findByReference(any())).thenReturn(result);
        when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));

        Email mappedEmail = new Email();
        mappedEmail.setBody("Hello World");
        mappedEmail.setEmailAddress("Sabo, Yaba");
        mappedEmail.setFrom("jerry@gmail.com");
        mappedEmail.setId("63e62275029e923494c88962");
        mappedEmail.setReference("05a045be-c295-4820-b9a2-222bb863b0fd");
        mappedEmail.setRetryLimit(0);
        mappedEmail.setStatus(EmailStatus.SENT);
        mappedEmail.setSubject("Hello from the Dreaming Spires");

        when(emailModelMapper.map((MessageRequest) any())).thenReturn(mappedEmail);
        assertThrows(MessagingException.class,
                () -> mailDevEmailSender.send(new MessageRequest("email", "jerry@gmail.com", "Hello World",
                        "Hello from the Dreaming Spires", "63e62275029e923494c88962", "08088876536", "Sabo, Yaba")));

        verify(emailRepository, times(1)).save(any());
        verify(emailRepository, times(1)).findByReference(any());
        verify(javaMailSender, times(1)).createMimeMessage();
        verify(emailModelMapper, times(1)).map((MessageRequest) any());
    }

    /**
     * Method under test: {@link MailDevEmailSender#send(MessageRequest)}
     */
    @Test
    void test_ValidatedEmailDispatchedSuccessfully() throws MessagingException {
        Email foundEmail = new Email();
        foundEmail.setBody("Hello World");
        foundEmail.setEmailAddress("dej@gmail.com");
        foundEmail.setFrom("jerry@gmail.com");
        foundEmail.setId("63e62275029e923494c88962");
        foundEmail.setReference("05a045be-c295-4820-b9a2-222bb863b0fd");
        foundEmail.setRetryLimit(0);
        foundEmail.setStatus(EmailStatus.SENT);
        foundEmail.setSubject("Hello from the Dreaming Spires");

        Optional<Email> result = Optional.of(foundEmail);

        when(emailRepository.findByReference(any())).thenReturn(result);
        when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));

        Email mappedEmail = new Email();
        mappedEmail.setBody("Hello World");
        mappedEmail.setEmailAddress("dej@gmail.com");
        mappedEmail.setFrom("jerry@gmail.com");
        mappedEmail.setId("63e62275029e923494c88962");
        mappedEmail.setReference("05a045be-c295-4820-b9a2-222bb863b0fd");
        mappedEmail.setRetryLimit(0);
        mappedEmail.setStatus(EmailStatus.SENT);
        mappedEmail.setSubject("Hello from the Dreaming Spires");

        when(emailModelMapper.map((MessageRequest) any())).thenReturn(mappedEmail);
        mailDevEmailSender.send(new MessageRequest("email", "jerry@gmail.com", "Hello World",
                "Hello from the Dreaming Spires", "63e62275029e923494c88962", "08088876536", "jerry@gmail.com"));
    }

    /**
     * Method under test: {@link MailDevEmailSender#findPendingEmails()}
     */
    @Test
    void test_FindPendingEmailsIsEmpty() {
        ArrayList<Email> emailList = new ArrayList<>();
        when(emailRepository.findPendingEmails()).thenReturn(emailList);
        List<Email> actualFindPendingEmailsResult = mailDevEmailSender.findPendingEmails();
        assertSame(emailList, actualFindPendingEmailsResult);
        assertTrue(actualFindPendingEmailsResult.isEmpty());
        verify(emailRepository, times(1)).findPendingEmails();
    }

    /**
     * Method under test: {@link MailDevEmailSender#save(Email)}
     */
    @Test
    void test_FindEmailByReference_Found_RetryLimitIncrements() {
        Email foundEmail = new Email();
        foundEmail.setBody("Hello World");
        foundEmail.setEmailAddress("dej@gmail.com");
        foundEmail.setFrom("jerry@gmail.com");
        foundEmail.setId("63e62275029e923494c88962");
        foundEmail.setReference("05a045be-c295-4820-b9a2-222bb863b0fd");
        foundEmail.setRetryLimit(0);
        foundEmail.setStatus(EmailStatus.SENT);
        foundEmail.setSubject("Hello from the Dreaming Spires");

        Optional<Email> result = Optional.of(foundEmail);
        when(emailRepository.findByReference(any())).thenReturn(result);

        Email myEmail = new Email();
        myEmail.setBody("Hello World");
        myEmail.setEmailAddress("dej@gmail.com");
        myEmail.setFrom("jerry@gmail.com");
        myEmail.setId("63e62275029e923494c88962");
        myEmail.setReference("05a045be-c295-4820-b9a2-222bb863b0fd");
        myEmail.setRetryLimit(0);
        myEmail.setStatus(EmailStatus.SENT);
        myEmail.setSubject("Hello from the Dreaming Spires");
        mailDevEmailSender.save(myEmail);

        Optional<Email> email = emailRepository.findByReference(myEmail.getReference());

        assertEquals(1, email.get().getRetryLimit());
        verify(emailRepository, times(2)).findByReference(any());
        verify(emailRepository, times(1)).save(any());
    }

    /**
     * Method under test: {@link MailDevEmailSender#save(Email)}
     */
    @Test
    void test_FindEmailByReference_NotFound_ThenSaveEmail() {
        Email savedEmail = new Email();
        savedEmail.setBody("Hello World");
        savedEmail.setEmailAddress("dej@gmail.com");
        savedEmail.setFrom("jerry@gmail.com");
        savedEmail.setId("63e62275029e923494c88962");
        savedEmail.setReference("05a045be-c295-4820-b9a2-222bb863b0fd");
        savedEmail.setRetryLimit(1);
        savedEmail.setStatus(EmailStatus.SENT);
        savedEmail.setSubject("Hello from the Dreaming Spires");

        when(emailRepository.findByReference(any())).thenReturn(Optional.empty());

        Email myEmail = new Email();
        myEmail.setBody("Hello World");
        myEmail.setEmailAddress("dej@gmail.com");
        myEmail.setFrom("jerry@gmail.com");
        myEmail.setId("63e62275029e923494c88962");
        myEmail.setReference("05a045be-c295-4820-b9a2-222bb863b0fd");
        myEmail.setRetryLimit(1);
        myEmail.setStatus(EmailStatus.SENT);
        myEmail.setSubject("Hello from the Dreaming Spires");
        mailDevEmailSender.save(myEmail);

        verify(emailRepository, times(1)).findByReference(any());
        verify(emailRepository, times(1)).save(any());

        assertEquals(savedEmail.getBody(), myEmail.getBody());
        assertEquals(savedEmail.getSubject(), myEmail.getSubject());
        assertEquals(savedEmail.getStatus(), myEmail.getStatus());
        assertEquals(savedEmail.getRetryLimit(), myEmail.getRetryLimit());
        assertEquals(savedEmail.getReference(), myEmail.getReference());
        assertEquals(savedEmail.getId(), myEmail.getId());
        assertEquals(savedEmail.getFrom(), myEmail.getFrom());
        assertEquals(savedEmail.getEmailAddress(), myEmail.getEmailAddress());
    }

}

