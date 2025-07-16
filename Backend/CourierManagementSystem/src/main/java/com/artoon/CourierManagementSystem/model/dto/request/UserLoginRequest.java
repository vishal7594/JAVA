package com.artoon.CourierManagementSystem.model.dto.request;

import lombok.Data;

@Data
public class UserLoginRequest {
    private  String username;
    private  String password;
    private String token; // Optional, can be used for token-based authentication
}
