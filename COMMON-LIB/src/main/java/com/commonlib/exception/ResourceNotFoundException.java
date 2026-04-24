package com.commonlib.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResourceNotFoundException extends RuntimeException{
    private final String message;
    private final HttpStatus status;

    public ResourceNotFoundException(String message){
        this.message = message;
        this.status = HttpStatus.NOT_FOUND;

    }
}
