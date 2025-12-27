package com.business_app.authservice.dto;

import java.util.Set;
import java.util.UUID;

public class UserResponse {
    private UUID id;
    private String username;
    private String email;
    private Set<String> roles;
}
