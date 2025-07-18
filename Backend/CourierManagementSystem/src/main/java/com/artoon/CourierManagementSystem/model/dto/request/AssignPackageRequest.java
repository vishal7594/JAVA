package com.artoon.CourierManagementSystem.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignPackageRequest {
    private Long agentId;
    private List<Long> packageIds;
}
