package com.artoon.CourierManagementSystem.model.dto.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
}
