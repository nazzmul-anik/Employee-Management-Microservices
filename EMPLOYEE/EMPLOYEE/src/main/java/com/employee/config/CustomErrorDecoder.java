package com.employee.config;

import com.employee.exception.CustomException;
import com.employee.exception.ErrorDecoderResponse;
import com.employee.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ObjectMapper mapper;

    public CustomErrorDecoder() {
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
    }
    @Override
    public Exception decode(String s, Response response) {
        int httpStatus = response.status();
        if(response.body() == null){
            return new CustomException("No response from Address service: "+httpStatus);
        }
        try(InputStream is = response.body().asInputStream()){
//            ErrorResponse errorResponse = mapper.readValue(is, ErrorResponse.class);
            // ✅ Read raw body first as String
            String rawBody = new String(is.readAllBytes(), StandardCharsets.UTF_8);

            // ✅ Handle empty body
            if (rawBody.trim().isEmpty()) {
                return new CustomException("Empty response from service.", httpStatus);
            }

            // ✅ Check if body is valid JSON before parsing
            if (!rawBody.trim().startsWith("{")) {
                // Not JSON — likely load balancer / Eureka error
                return new CustomException(
                        "Service unavailable. Please try later.", httpStatus);
            }
            ErrorDecoderResponse errorDecoderResponse = mapper.readValue(is, ErrorDecoderResponse.class);
            String message = errorDecoderResponse.getMessage() != null ? errorDecoderResponse.getMessage() : "Error from Address Service.";
            throw new CustomException(message, httpStatus);
        }catch (IOException e){
            e.printStackTrace();
            throw  new CustomException("Failed to parse Address Service error response: ", httpStatus);
        }
    }
}
