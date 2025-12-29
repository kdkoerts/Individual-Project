package nl.sogyo.ip.persistence;

import  nl.sogyo.ip.interfaces.IdentityRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
@Profile("memory")
public class InMemoryIdentityRepository implements IdentityRepository {

    private final Map<String, String> store = new HashMap<>();

    @Override
    public void save(String identity) {
        store.put(identity, identity);
        System.out.println("Saved identity in memory: " + identity);
    }

    @Override
    public Optional<String> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public boolean existsByPublicKey(String publicKey) {
        return store.containsKey(publicKey);
    }
}
