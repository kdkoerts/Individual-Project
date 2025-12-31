package nl.sogyo.ip.persistence.adapters;

import nl.sogyo.domain.User;
import nl.sogyo.ip.interfaces.UserRepository;
import nl.sogyo.ip.persistence.SpringDataUserRepository;
import nl.sogyo.ip.persistence.entities.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaUserRepository implements UserRepository {

    private final SpringDataUserRepository jpa;

    public JpaUserRepository(SpringDataUserRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public User save(User user) {
        return toDomain(jpa.save(toEntity(user)));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return jpa.findByUsername(username).map(this::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    private User toDomain(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getFirstname(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getUsername(),
                entity.getPassword()
        );
    }

    private UserEntity toEntity(User user) {
        UserEntity e = new UserEntity();
        e.setId(user.id());
        e.setFirstname(user.firstname());
        e.setLastName(user.lastName());
        e.setEmail(user.email());
        e.setUsername(user.username());
        e.setPassword(user.password());
        return e;
    }
}


