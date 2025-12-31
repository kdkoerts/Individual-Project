package nl.sogyo.ip.web;

import java.util.Arrays;

import nl.sogyo.domain.User;
import nl.sogyo.ip.interfaces.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "nl.sogyo.ip") // scans everything under nl.sogyo.ip
@EnableJpaRepositories(basePackages = "nl.sogyo.ip.persistence")
@EntityScan(basePackages = "nl.sogyo.ip.persistence.entities")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner runner(UserRepository repo) {
        return args -> {
            // save a test user
            repo.save(new User(null, "John", "Doe", "john@example.com", "johndoe", "password123"));

            // fetch by username
            repo.findByUsername("johndoe").ifPresent(u ->
                    System.out.println("Found user: " + u.username())
            );
        };
    }
}
