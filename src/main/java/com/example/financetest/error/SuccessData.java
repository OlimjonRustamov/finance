package com.example.financetest.error;

import lombok.Data;

@Data
public class SuccessData {

    private String message = "Success";

    private int status = 200;


    public SuccessData(String message) {
        this.message = message;
    }

    public SuccessData() {

    }

}
