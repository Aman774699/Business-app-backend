package com.business_app.authservice.mapper;

import com.business_app.authservice.dto.LoginRequest;
import com.business_app.authservice.dto.RegisterRequest;
import com.business_app.authservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    PasswordEncoder passwordEncoder;

    public User toEntity(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        return user;
    }
    public RegisterRequest toRegisterRequest(User user) {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername(user.getUsername());
        registerRequest.setPassword(user.getPassword());
        registerRequest.setEmail(user.getEmail());
        return registerRequest;
    }
    public User toEntity(LoginRequest loginRequest) {
        User user = new User();
        user.setUsername(loginRequest.getEmail());
        user.setPassword(loginRequest.getPassword());
        return user;
    }
    public LoginRequest toLoginRequest(User user) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(user.getUsername());
        loginRequest.setPassword(user.getPassword());
        return loginRequest;
    }
}
