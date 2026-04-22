package com.auth.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse {
    private final String message;
    private final HttpStatus status;
    private final LocalDateTime timestamp;

    public ErrorResponse(String message, HttpStatus status){
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }
}
