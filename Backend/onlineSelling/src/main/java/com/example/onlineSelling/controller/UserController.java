package com.example.onlineSelling.controller;

import com.example.onlineSelling.dto.ApiResponse;
import com.example.onlineSelling.dto.UserRequest;
import com.example.onlineSelling.dto.UserResponse;
import com.example.onlineSelling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/add-user")
    public ApiResponse<UserResponse> addCustomer(@RequestBody UserRequest request)
    {
           return userService.addCustomer(request);
    }
}
