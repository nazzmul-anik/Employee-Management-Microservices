package com.employee.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {
    private final int status;
    public CustomException(String message){
        super(message);
        this.status = 500;
    }
    public CustomException(String message, int status){
        super(message);
        this.status = status;
    }
}
