package com.example.financetest.merchant;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class InsertMerchantDto {

    @NotNull(message = "Name must not be null")
    private String name;

    @NotNull(message = "Phone must not be null")
    private String phone;

    private String info;
}
