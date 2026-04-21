package com.address.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorDecoderResponse {
    private final String message;
    private final int status;
    private final LocalDateTime timestamp;

    public ErrorDecoderResponse(String message, int status){
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }
}
