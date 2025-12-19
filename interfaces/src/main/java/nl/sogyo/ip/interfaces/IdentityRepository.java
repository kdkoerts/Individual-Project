package nl.sogyo.ip.interfaces;

import java.util.Optional;

public interface IdentityRepository {

    void save(String identity);

    Optional<String> findById(String id);

    boolean existsByPublicKey(String publicKey);
}