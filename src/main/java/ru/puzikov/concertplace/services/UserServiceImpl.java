package ru.puzikov.concertplace.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.puzikov.concertplace.models.AppUser;
import ru.puzikov.concertplace.models.Role;
import ru.puzikov.concertplace.repos.RoleRepo;
import ru.puzikov.concertplace.repos.UserRepo;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo userRepo;

    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser saveUser(AppUser user) {
        log.info("new user: ", user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("new role: ", role);
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("new role for user: ", roleName, " ", username);
        AppUser user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);

    }

    @Override
    public AppUser getUser(String username) {
        log.info("get user: ", username);
        return userRepo.findByUsername(username);
    }

    @Override
    public List<AppUser> getUsers() {
        log.info("all users");
        return userRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepo.findByUsername(username);
        if (user == null) {
            log.error("user not found");
            throw new UsernameNotFoundException("User not found");
        } else {
            log.info("User found {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role ->
                authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
