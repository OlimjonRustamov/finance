package com.example.financetest.error;

import com.example.financetest.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class CustomError {

//    private LocalDateTime timestamp;

    @JsonView({Views.Public.class, Views.Internal.class})
    private int status;

    @JsonView({Views.Public.class, Views.Internal.class})
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