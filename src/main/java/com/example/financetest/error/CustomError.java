package com.example.financetest.error;

import lombok.Data;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class CustomError {

//    private LocalDateTime timestamp;

    private int status;

    private String error;

    public CustomError(String error) {
        this.error = error;
//        this.timestamp = LocalDateTime.now();
    }

    public CustomError(String error, int status) {
        this.status = status;
        this.error = error;
//        this.timestamp = LocalDateTime.now();
    }

    public CustomError() {

    }
}