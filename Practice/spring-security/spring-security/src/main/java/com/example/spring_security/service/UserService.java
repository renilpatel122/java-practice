package com.example.spring_security.service;

import com.example.spring_security.entity.User;
import com.example.spring_security.entity.dtos.UserDetails;

public interface UserService {
    UserDetails getUserDetails(String email);
}
