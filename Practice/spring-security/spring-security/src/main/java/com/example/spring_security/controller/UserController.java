package com.example.spring_security.controller;

import com.example.spring_security.entity.User;
import com.example.spring_security.entity.dtos.UserDetails;
import com.example.spring_security.repository.UserRepository;
import com.example.spring_security.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user-controller")
@PreAuthorize("hasRole('USER')")
@Tag(name = "User")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/{email}")
  @PreAuthorize("hasAuthority('user:read')")
  public ResponseEntity<UserDetails> getUserDetails(@PathVariable("email") String email) {
    return ResponseEntity.ok(userService.getUserDetails(email));
  }


}
