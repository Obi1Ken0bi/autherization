package ru.puzikov.concertplace.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.puzikov.concertplace.models.Role;

@Service
public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
