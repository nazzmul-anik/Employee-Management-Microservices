package com.auth.model.dto;

import lombok.Data;

@Data
public class JwtTokenResponse {
    private String token;
    private String type;
    private String validUntil;
}
