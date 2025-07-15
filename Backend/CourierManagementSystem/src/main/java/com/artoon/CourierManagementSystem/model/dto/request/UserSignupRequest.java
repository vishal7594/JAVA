package com.artoon.CourierManagementSystem.model.dto.request;

import com.artoon.CourierManagementSystem.enums.Role;
import lombok.Data;

@Data
public class UserSignupRequest {
    private  String username;
    private  String password;
    private Role role;
}
