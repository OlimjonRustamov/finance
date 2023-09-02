package com.example.financetest.user;

import com.example.financetest.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PreAuthorize("hasAuthority('VIEW_ADMINS')")
    @JsonView(Views.Public.class)
    @GetMapping("/view/all")
    HttpEntity<?> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }
}
