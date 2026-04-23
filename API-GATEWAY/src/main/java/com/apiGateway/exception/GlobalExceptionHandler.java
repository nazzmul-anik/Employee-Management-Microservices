package com.apiGateway.exception;

import com.apiGateway.exception.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException ex){
        ErrorResponse response = new ErrorResponse(ex.getMessage(), ex.getStatus());
        return new ResponseEntity<>(response, response.getStatus());
    }

}
