package com.artoon.CourierManagementSystem.controller.auth;

import com.artoon.CourierManagementSystem.jwt.JwtHelper;
import com.artoon.CourierManagementSystem.model.dto.request.UserRequest;
import com.artoon.CourierManagementSystem.model.dto.response.ApiResponse;
import com.artoon.CourierManagementSystem.model.dto.response.UserResponse;
import com.artoon.CourierManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {




    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ApiResponse<UserResponse> registerUser(@RequestBody UserRequest userRequest) {

        try {
            return  new ApiResponse<>(true, "User registered successfully", userService.registerUser(userRequest));
        }catch (Exception e)
        {
            return  new ApiResponse<>(false, "User registration failed: " + e.getMessage(), null);
        }
    }

}
