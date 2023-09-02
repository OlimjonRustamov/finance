package com.example.financetest.auth;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginRequest {

    @NotNull(message = "username must not be empty")
    private String username;
    @NotNull(message = "password must not be empty")
    private String password;
}
