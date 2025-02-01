package com.example.spring_security.service.impl;

import com.example.spring_security.entity.User;
import com.example.spring_security.entity.dtos.UserDetails;
import com.example.spring_security.repository.UserRepository;
import com.example.spring_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails getUserDetails(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserDetails(user.getFirstname(), user.getLastname(), user.getEmail(), user.getRole());
    }
}
