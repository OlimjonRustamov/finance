package com.example.financetest.auth;

import com.example.financetest.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    @JsonView(Views.Internal.class)
    public HttpEntity<?> login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
