package com.artoon.CourierManagementSystem.model.dto.request;

import lombok.Data;

@Data
public class UserRequest {
    private  String username;
    private  String password;
    private  String role;
}
