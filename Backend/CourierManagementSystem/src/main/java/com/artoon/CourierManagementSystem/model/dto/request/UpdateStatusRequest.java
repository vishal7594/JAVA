package com.artoon.CourierManagementSystem.model.dto.request;

import com.artoon.CourierManagementSystem.enums.PackageStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateStatusRequest {
    private Long assignmentId;
    private PackageStatus status;
}
