package africa.semicolon.notification.sender;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SenderRepository extends MongoRepository<Sender, String> {

    Optional<Sender> findByEmailAddressIgnoreCase(String emailAddress);
}
