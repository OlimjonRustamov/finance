package com.example.financetest.auth;

import com.example.financetest.error.CustomError;
import com.example.financetest.user.User;
import com.example.financetest.user.UserRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    PasswordEncoder passwordEncoder;
    UserRepository userRepository;

    public AuthServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public HttpEntity<?> login(LoginRequest request) {
        Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(401).body(
                        new CustomError("Incorrect login or password", 401)
                );
            }
        } else {
            return ResponseEntity.status(401).body(
                    new CustomError("Username does not exist", 401)
            );
        }
    }
}
