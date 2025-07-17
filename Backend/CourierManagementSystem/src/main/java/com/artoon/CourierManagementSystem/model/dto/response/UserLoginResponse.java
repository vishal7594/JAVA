package com.artoon.CourierManagementSystem.model.dto.response;

import com.artoon.CourierManagementSystem.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResponse {
    private Long id;
    private String username;
    private Role role;
    private String token;
}
