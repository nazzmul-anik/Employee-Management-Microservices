package com.commonlib.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MissingParameterException extends RuntimeException{
    private final String message;
    private final HttpStatus status;
    public MissingParameterException(String message) {
        this.message = message;
        this.status = HttpStatus.BAD_REQUEST;
    }
}
