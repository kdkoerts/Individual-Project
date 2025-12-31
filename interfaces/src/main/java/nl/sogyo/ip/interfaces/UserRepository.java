package nl.sogyo.ip.interfaces;

import nl.sogyo.domain.User;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}