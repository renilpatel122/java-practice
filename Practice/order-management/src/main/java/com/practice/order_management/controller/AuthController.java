package com.practice.order_management.controller;


import com.practice.order_management.security.JwtUtil;
import com.practice.order_management.utill.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Map<String, String>>> login(@RequestBody Map<String, String> credentials) {
        try {
            String username = credentials.get("username");
            String password = credentials.get("password");

            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = jwtUtil.generateToken(auth.getName());
            Map<String, String> tokenMap = new HashMap<>();
            tokenMap.put("token", token);

            return ResponseEntity.ok(ApiResponse.success(Map.of("token", token),"Success to get token"));
        } catch (AuthenticationException e) {
            return ResponseEntity.ok(ApiResponse.fail(null,"Invalid username or password"));
        }
    }
}

