package com.address.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex){
        ErrorResponse response = new ErrorResponse(ex.getMessage(), ex.getStatus());
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException ex){
        ErrorResponse response = new ErrorResponse(ex.getMessage(), ex.getStatus());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(MissingParameterException.class)
    public ResponseEntity<?> handleMissingParameterException(MissingParameterException ex){
        ErrorResponse response = new ErrorResponse(ex.getMessage(), ex.getStatus());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleCustomException(CustomException ex){
        ErrorDecoderResponse response = new ErrorDecoderResponse(ex.getMessage(), ex.getStatus());
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}