package com.artoon.CourierManagementSystem.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Role {
    CUSTOMER,
    ADMIN,
    MANAGER,
    DELIVERY_AGENT;

    public String toUpperCase() {
        return this.name().toUpperCase();
    }

    @JsonCreator
    public static Role fromString(String value) {
        for (Role role : Role.values()) {
            if (role.name().equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid role: " + value);
    }
}
