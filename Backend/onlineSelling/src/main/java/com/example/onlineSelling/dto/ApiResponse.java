package com.example.onlineSelling.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ApiResponse<T> {
    private  boolean status;
    private  String message;
    private  T data;
}
