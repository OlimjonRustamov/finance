package com.example.financetest.user;

import com.example.financetest.error.CustomError;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public HttpEntity<?> getAllUsers(User user) {
        return ResponseEntity.ok(userRepository.findByIdNot(user.getId()));
    }
    @Override
    public HttpEntity<?> insertUser(InsertUserDto dto) {
        Optional<Role> optionalRole = roleRepository.findById(dto.getRoleId());
        if(!optionalRole.isPresent()) {
            return ResponseEntity.status(400).body(
                    new CustomError("Role does not exist", 400)
            );
        }
        if(userRepository.existsByUsername(dto.getUsername()))
            return ResponseEntity.status(400).body(new CustomError("username already exist", 400));
        Role role = optionalRole.get();
        User user = new User();
        user.setEnabled(true);
        user.setRole(role);
        user.setUsername(dto.getUsername());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setFullName(dto.getFullName());
        user = userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @Override
    public HttpEntity<?> toggleEnabled(int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(!optionalUser.isPresent()) return ResponseEntity.status(400).body(
                new CustomError("User does not exist", 400)
        );
        User user = optionalUser.get();
        user.setEnabled(!user.isEnabled());
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }
}
