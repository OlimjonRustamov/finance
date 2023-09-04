package com.example.financetest.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ChangePasswordDto {

    @NotNull(message = "oldPassword must not be null")
    @JsonProperty("old_password")
    private String oldPassword;

    @NotNull(message = "newPassword must not be null")
    @JsonProperty("new_password")
    private String newPassword;

}
