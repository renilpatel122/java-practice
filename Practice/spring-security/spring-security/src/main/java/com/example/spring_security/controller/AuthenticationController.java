package com.example.spring_security.controller;

import com.example.spring_security.entity.dtos.AuthenticationRequest;
import com.example.spring_security.entity.dtos.AuthenticationResponse;
import com.example.spring_security.entity.dtos.RegisterRequest;
import com.example.spring_security.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticateUser(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }

}


