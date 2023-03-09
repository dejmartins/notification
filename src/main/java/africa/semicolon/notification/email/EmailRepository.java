package africa.semicolon.notification.email;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends MongoRepository<Email, String> {

  @Query("{status:PENDING}")
  List<Email> findPendingEmails();

  Optional<Email> findByReference(String reference);
}
