package com.example.financetest.profile;

import com.example.financetest.error.CustomError;
import com.example.financetest.error.SuccessData;
import com.example.financetest.user.User;
import com.example.financetest.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public ProfileServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public HttpEntity<?> getMe(User user) {
        return ResponseEntity.ok(user);
    }

    @Override
    public HttpEntity<?> changePassword(User user, ChangePasswordDto dto) {
        if (passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
            user = userRepository.save(user);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new CustomError("Incorrect current password inserted. Please check old password is correct", 406));
        }
    }

    @Override
    public HttpEntity<?> logout(User user) {
        user.setAccessToken(null);
        userRepository.save(user);
        return ResponseEntity.ok(new SuccessData("Token is made expired"));
    }
}
