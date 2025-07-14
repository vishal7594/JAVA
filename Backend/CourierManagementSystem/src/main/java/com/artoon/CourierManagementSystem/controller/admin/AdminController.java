package com.artoon.CourierManagementSystem.controller.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/admin")
public class AdminController {
    // This controller can be used to handle admin-specific requests
    // For example, you can add methods to manage users, view reports, etc.

    // Example method to return a welcome message
    @RequestMapping("/welcome")
    public String welcome() {
        return "Welcome to the Admin Dashboard!";
    }

    // Additional admin-related endpoints can be added here
}
