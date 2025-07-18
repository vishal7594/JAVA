package com.artoon.CourierManagementSystem.model.dto.response;

import com.artoon.CourierManagementSystem.enums.PackageStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AssignmentResponse {
    private Long id;
    private PackageResponse packageDetail;
    private Long agentId;
    private String agentName;
    private LocalDateTime assignedAt;
}
