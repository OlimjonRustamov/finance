package com.example.financetest.profile;

import com.example.financetest.user.User;
import org.springframework.http.HttpEntity;

public interface ProfileService {

    HttpEntity<?> getMe(User user);

    HttpEntity<?> changePassword(User user, ChangePasswordDto dto);

}
