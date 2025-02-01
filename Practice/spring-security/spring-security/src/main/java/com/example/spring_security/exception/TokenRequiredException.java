package com.example.spring_security.exception;

public class TokenRequiredException extends RuntimeException {
    public TokenRequiredException(String message) {
        super(message);
    }
}

