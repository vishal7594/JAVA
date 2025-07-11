package com.artoon.CourierManagementSystem.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/welcome")
    public String welcome() {
        return "Welcome to the User Dashboard!";
    }
}
