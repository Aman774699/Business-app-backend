package com.business_app.authservice.controller;


import com.business_app.authservice.constants.Apipaths;
import com.business_app.authservice.dto.AuthResponse;
import com.business_app.authservice.dto.LoginRequest;
import com.business_app.authservice.dto.RegisterRequest;
import com.business_app.authservice.service.Authservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Apipaths.AUTH_BASE)
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final Authservice authservice;

    public AuthController(Authservice authservice) {
        this.authservice = authservice;
    }

    @PostMapping(Apipaths.REGISTER)
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        logger.info(" Register new user request received "+request.getEmail());
        authservice.register(request);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping(Apipaths.LOGIN)
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        logger.info(" Login request received "+request.getEmail());
        String token = authservice.login(request);
        return ResponseEntity.ok(new AuthResponse(token));
    }

}
