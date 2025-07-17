package com.artoon.CourierManagementSystem.util;

import com.artoon.CourierManagementSystem.model.entity.User;
import com.artoon.CourierManagementSystem.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public  class AuthHelper {
    public static User getUserFromAuth(Authentication authentication,UserRepository userRepository) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User not authenticated");
        }

        String username = authentication.getName();
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        return user;
    }
}
