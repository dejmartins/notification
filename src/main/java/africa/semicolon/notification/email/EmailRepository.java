package africa.semicolon.notification.email;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmailRepository extends MongoRepository<Email, String> {

    @Query("{hasSent:false}")
    List<Email> findUnsentEmails();
    Optional<Email> findByEmailAddressIgnoreCase(String emailAddress);

}
