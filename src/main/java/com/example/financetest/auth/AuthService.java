package com.example.financetest.auth;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthService {

    HttpEntity<?> login(@RequestBody LoginRequest request);

}
