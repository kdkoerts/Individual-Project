package nl.sogyo.ip.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
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
}
