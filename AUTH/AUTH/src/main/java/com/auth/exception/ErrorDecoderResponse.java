package com.employee.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorDecoderResponse {
    private String message;
    private int status;
    private LocalDateTime timestamp;

    public ErrorDecoderResponse(String message, int status){
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }
}
