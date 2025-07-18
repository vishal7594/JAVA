package com.artoon.CourierManagementSystem.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class PaginatedResponse <T>{
    private List<T> content;
    private int page;
    private int size;
    private long numberOfElements;
    private long totalElements;
    private int totalPages;
}
