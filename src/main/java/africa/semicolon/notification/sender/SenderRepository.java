package africa.semicolon.notification.sender;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SenderRepository extends MongoRepository<Sender, String> {

  Optional<Sender> findByEmailAddressIgnoreCase(String emailAddress);
}
