package com.example.spring_security.service;

import com.example.spring_security.entity.dtos.AuthenticationRequest;
import com.example.spring_security.entity.dtos.AuthenticationResponse;
import com.example.spring_security.entity.dtos.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
