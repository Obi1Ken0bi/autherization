package ru.puzikov.concertplace.services;

import ru.puzikov.concertplace.models.AppUser;
import ru.puzikov.concertplace.models.Role;

import java.util.List;

public interface UserService {
    AppUser saveUser(AppUser user);

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    AppUser getUser(String username);

    List<AppUser> getUsers();


}
