package com.example.financetest.user;

import org.springframework.http.HttpEntity;

public interface UserService {

    HttpEntity<?> getAllUsers(User user);

    HttpEntity<?> insertUser(InsertUserDto dto);

    HttpEntity<?> toggleEnabled(int userId);

}
