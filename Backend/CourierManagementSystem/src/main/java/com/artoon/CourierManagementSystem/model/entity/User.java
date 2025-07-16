package com.artoon.CourierManagementSystem.model.entity;

import com.artoon.CourierManagementSystem.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name =  "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private String token;
}
