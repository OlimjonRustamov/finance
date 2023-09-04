package com.example.financetest.initializer;

import com.example.financetest.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Component
public class DataLoader implements CommandLineRunner {

    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!roleRepository.existsByName("admin")) {
            Role role = new Role();
            role.setName("admin");
            role.setPermissionList(Arrays.asList(Permission.values()));
            roleRepository.save(role);

            if (!userRepository.existsByUsername("olimjon_rustamov")) {
                User user = new User();
                user.setUsername("olimjon_rustamov");
                user.setFullName("Olimjon Rustamov");
                user.setPhoneNumber("+998900123477");
                user.setPassword(passwordEncoder.encode("123"));
                user.setEnabled(true);
                user.setRole(role);
                user.setAccessToken(UUID.randomUUID().toString());
                userRepository.save(user);
            }
        }
    }
}
