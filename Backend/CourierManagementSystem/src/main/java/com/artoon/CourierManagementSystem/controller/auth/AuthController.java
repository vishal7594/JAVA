package com.artoon.CourierManagementSystem.controller.auth;

import com.artoon.CourierManagementSystem.model.dto.request.UserLoginRequest;
import com.artoon.CourierManagementSystem.model.dto.request.UserSignupRequest;
import com.artoon.CourierManagementSystem.model.dto.response.ApiResponse;
import com.artoon.CourierManagementSystem.model.dto.response.UserLoginResponse;
import com.artoon.CourierManagementSystem.model.dto.response.UserSignupResponse;
import com.artoon.CourierManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ApiResponse<UserSignupResponse> registerUser(@RequestBody UserSignupRequest userRequest) {
        System.out.println("role: " + userRequest.getRole());
        try {
            return  new ApiResponse<>(true, "User registered successfully", userService.registerUser(userRequest));
        }catch (Exception e)
        {
            return  new ApiResponse<>(false, "User registration failed: " + e.getMessage(), null);
        }
    }

    @PostMapping("/login")
    public  ApiResponse<UserLoginResponse> loginUser(@RequestBody(required = false) UserLoginRequest userRequest,
                                                     @RequestHeader(value = "Authorization", required = false) String token) {
        try {
            return  new ApiResponse<>(true, "User login successfully", userService.loginUser(userRequest,token));
        }catch (Exception e)
        {
            System.out.println("Error during user login: " + e.getMessage());
            return  new ApiResponse<>(false, "User login failed: " + e.getMessage(), null);
        }
    }
}
