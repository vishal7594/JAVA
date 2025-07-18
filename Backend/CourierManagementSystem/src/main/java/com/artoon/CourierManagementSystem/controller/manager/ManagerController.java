package com.artoon.CourierManagementSystem.controller.manager;

import com.artoon.CourierManagementSystem.model.dto.request.AssignPackageRequest;
import com.artoon.CourierManagementSystem.model.dto.response.ApiResponse;
import com.artoon.CourierManagementSystem.model.dto.response.AssignmentResponse;
import com.artoon.CourierManagementSystem.model.dto.response.PaginatedResponse;
import com.artoon.CourierManagementSystem.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    PackageService packageService;

    @PostMapping("/assign-package")
    public ApiResponse<String> assignPackageToAgent(@RequestBody AssignPackageRequest request)
    {
        try {
            System.out.println("Assign package request received: " + request);
            packageService.assignPackageToAgent(request);
            return new ApiResponse<>(true, "Package assigned successfully", null);

        } catch (Exception e) {
            return  new ApiResponse<>(false, "Failed to assign package: " + e.getMessage(), null);
        }
    }

    @GetMapping("/assignments/{page}/{size}")
    public  ApiResponse<PaginatedResponse<AssignmentResponse>> getAllAssignments(@PathVariable int page, @PathVariable int size) {
        try {
            System.out.println("Get all packages request received");
            PaginatedResponse<AssignmentResponse> response = packageService.getAllAssignments(page, size);
            return new ApiResponse<>(true, "Fetched all packages successfully", response);
        } catch (Exception e) {
            return new ApiResponse<>(false, "Failed to fetch packages: " + e.getMessage(), null);
        }
    }

}
