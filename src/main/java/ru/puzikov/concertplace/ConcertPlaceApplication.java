package ru.puzikov.concertplace;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.puzikov.concertplace.models.AppUser;
import ru.puzikov.concertplace.models.Role;
import ru.puzikov.concertplace.services.UserService;

import java.util.ArrayList;

@SpringBootApplication
public class ConcertPlaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConcertPlaceApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.saveRole(new Role("ROLE_USER"));
            userService.saveRole(new Role("ROLE_MANAGER"));
            userService.saveRole(new Role("ROLE_ADMIN"));
            userService.saveRole(new Role("ROLE_SUPER_ADMIN"));

            userService.saveUser(new AppUser("Artem Puzikov", "ubik33", "1234", new ArrayList<>()));
            userService.saveUser(new AppUser("Artem Buzikov", "ubik34", "1234", new ArrayList<>()));
            userService.saveUser(new AppUser("Artem Puzikov", "ubik35", "1234", new ArrayList<>()));
            userService.saveUser(new AppUser("Artem Puzikov", "ubik36", "4321", new ArrayList<>()));


            userService.addRoleToUser("ubik33", "ROLE_USER");
            userService.addRoleToUser("ubik34", "ROLE_MANAGER");
            userService.addRoleToUser("ubik35", "ROLE_ADMIN");
            userService.addRoleToUser("ubik36", "ROLE_USER");
            userService.addRoleToUser("ubik36", "ROLE_MANAGER");
            userService.addRoleToUser("ubik36", "ROLE_ADMIN");
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
