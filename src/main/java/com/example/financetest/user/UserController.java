package com.example.financetest.user;

import com.example.financetest.Views;
import com.example.financetest.annotation.CurrentUser;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('VIEW_ADMINS')")
    @JsonView(Views.Public.class)
    @GetMapping("/view/all")
    HttpEntity<?> getAllUsers(@CurrentUser User user) {
        return userService.getAllUsers(user);
    }

    @PreAuthorize("hasAuthority('ADD_ADMIN')")
    @PostMapping("/insert")
    @JsonView(Views.Public.class)
    HttpEntity<?> insertUser(@Valid @RequestBody InsertUserDto dto) {
        return userService.insertUser(dto);
    }


    @PreAuthorize("hasAuthority('EDIT_ADMIN')")
    @JsonView(Views.Public.class)
    @PutMapping("/toggle-enabled")
    HttpEntity<?> toggleEnabled(@RequestParam("user_id") int userId) {
        return userService.toggleEnabled(userId);
    }
}
