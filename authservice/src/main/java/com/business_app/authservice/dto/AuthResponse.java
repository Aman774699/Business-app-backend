package com.business_app.authservice.dto;

import lombok.Data;

@Data
public class AuthResponse {
    public AuthResponse(String token) {
        this.token = token;
    }

    private String token;
    private String tokenType = "Bearer";

    public AuthResponse(String token, String tokenType) {
        this.token = token;
        this.tokenType = tokenType;
    }
}
