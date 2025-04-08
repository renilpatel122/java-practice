package com.practice.order_management.service;

import com.practice.order_management.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    List<User>  getAllUsers();
    Optional<User> getUserById(Long id);
    User updateUser(Long id, User user);
    String deleteUser(Long id);
}
