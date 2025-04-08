package com.practice.order_management.controller;
import com.practice.order_management.entity.User;
import com.practice.order_management.service.UserService;
import com.practice.order_management.utill.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(ApiResponse.success(createdUser, "User created successfully"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        return ResponseEntity.ok(ApiResponse.success(userService.getAllUsers(), "Users retrieved successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Optional<User>>> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(userService.getUserById(id), "User retrieved successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(ApiResponse.success(updatedUser, "User updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(null, userService.deleteUser(id)));
    }
}

