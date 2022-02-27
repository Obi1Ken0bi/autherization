package ru.puzikov.concertplace.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.puzikov.concertplace.models.AppUser;

@Service
public interface UserRepo extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
