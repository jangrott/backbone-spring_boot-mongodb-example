package pl.jangrot.lnksmgmt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;


@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private LinkRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();

        repository.save(new Link(randomStringUUID(), "http://backbonejs.org/"));
        repository.save(new Link(randomStringUUID(), "http://projects.spring.io/spring-boot/", true));
        repository.save(new Link(randomStringUUID(), "https://www.mongodb.org/", false));
    }

    private String randomStringUUID() {
        return UUID.randomUUID().toString();
    }
}
