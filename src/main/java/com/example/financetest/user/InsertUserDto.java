package com.example.financetest.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class InsertUserDto {

    @NotNull
    @JsonProperty("role_id")
    private int roleId;

    @NotNull
    private String username;

    @NotNull
    @JsonProperty("full_name")
    private String fullName;

    @NotNull
    @JsonProperty
    private String password;

    @NotNull
    @JsonProperty("phone_number")
    private String phoneNumber;
}
