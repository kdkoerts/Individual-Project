package nl.sogyo.ip.application;

import nl.sogyo.domain.User;
import nl.sogyo.ip.application.dto.UserRegistrationDTO;
import nl.sogyo.ip.interfaces.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repo;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User register(UserRegistrationDTO dto) {
        if (repo.findByUsername(dto.username()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        String hashed = passwordEncoder.encode(dto.password());

        User user = new User(
                null,
                dto.firstname(),
                dto.lastName(),
                dto.email(),
                dto.username(),
                hashed
        );

        return repo.save(user);
    }

    // Login
    public boolean login(String username, String password) {
        Optional<User> userOpt = repo.findByUsername(username);
        if (userOpt.isEmpty()) return false;
        User user = userOpt.get();
        return passwordEncoder.matches(password, user.password());
    }
}
