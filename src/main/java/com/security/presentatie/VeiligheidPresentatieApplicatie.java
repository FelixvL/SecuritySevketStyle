package com.security.presentatie;

import com.security.presentatie.persistence.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VeiligheidPresentatieApplicatie {

    public static void main(String[] args) {
        SpringApplication.run(VeiligheidPresentatieApplicatie.class, args);
    }

    @Bean
    CommandLineRunner init(UserService userService) {
        return (args) -> userService.initUsers();
    }
}
