package com.artoon.CourierManagementSystem.model.dto.response;

import com.artoon.CourierManagementSystem.enums.PackageStatus;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PackageResponse {
    private Long id;
    private String description;
    private String sourceAddress;
    private String destinationAddress;
    private String currentLocation;
    private PackageStatus status;
}
