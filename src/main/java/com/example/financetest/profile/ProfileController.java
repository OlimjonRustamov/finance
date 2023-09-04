package com.example.financetest.profile;

import com.example.financetest.Views;
import com.example.financetest.annotation.CurrentUser;
import com.example.financetest.user.User;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/me")
    @JsonView(Views.Public.class)
    HttpEntity<?> getMe(@CurrentUser User user) {
        return profileService.getMe(user);
    }

    @PostMapping("/change-password")
    @JsonView(Views.Public.class)
    HttpEntity<?> changePassword(@CurrentUser User user, @Valid @RequestBody ChangePasswordDto dto) {
        return profileService.changePassword(user, dto);
    }
}
