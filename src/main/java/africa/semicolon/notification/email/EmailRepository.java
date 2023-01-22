package africa.semicolon.notification.email;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends MongoRepository<ScheduledEmailRequest, String> {

    @Query("{hasSent:false}")
    Iterable<ScheduledEmailRequest> findUnsentEmails();

}
