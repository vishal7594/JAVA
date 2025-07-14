package com.artoon.CourierManagementSystem.model.dto.response;

import lombok.Data;

@Data
public class UserResponse {
    private Integer id;
    private String username;
    private String role;
    private String token;

}
