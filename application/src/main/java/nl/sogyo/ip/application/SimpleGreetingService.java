package nl.sogyo.ip.application;

import nl.sogyo.ip.interfaces.GreetingService;
import org.springframework.stereotype.Service;

@Service
public class SimpleGreetingService implements GreetingService {
    @Override
    public String greet(String name) {
        return "Hello, " + name + "!";
    }
}
