package com.business_app.authservice.service;

import com.business_app.authservice.dto.LoginRequest;
import com.business_app.authservice.dto.RegisterRequest;
import com.business_app.authservice.mapper.UserMapper;
import com.business_app.authservice.model.Role;
import com.business_app.authservice.model.User;
import com.business_app.authservice.repository.RoleRepository;
import com.business_app.authservice.repository.UserRepository;
import com.business_app.authservice.security.JwtUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class Authservice {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    private JwtUtils jwtUtil;
    private UserMapper userMapper;

    public Authservice(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, JwtUtils jwtUtil, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.jwtUtil = jwtUtil;
        this.userMapper = userMapper;
    }

    public void register(RegisterRequest request) {

        Role role = roleRepository.findByName("CUSTOMER")
                .orElseThrow(() -> new RuntimeException("Role not found"));
        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Set.of(role));
        userRepository.save(user);
    }

    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return jwtUtil.generateToken(user.getUsername());
    }
}
