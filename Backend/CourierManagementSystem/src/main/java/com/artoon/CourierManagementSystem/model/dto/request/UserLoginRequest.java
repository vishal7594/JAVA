package com.artoon.CourierManagementSystem.model.dto.request;

import lombok.Data;

@Data
public class UserLoginRequest {
    private  String username;
    private  String password;
}
