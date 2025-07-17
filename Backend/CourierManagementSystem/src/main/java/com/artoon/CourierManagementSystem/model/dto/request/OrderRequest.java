package com.artoon.CourierManagementSystem.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private List<PackageRequest> packages;

    @Data
    public static class PackageRequest {
        private String description;
        private String sourceAddress;
        private String destinationAddress;
    }
}
