package com.artoon.CourierManagementSystem.controller.customer;

import com.artoon.CourierManagementSystem.model.dto.response.ApiResponse;
import com.artoon.CourierManagementSystem.model.dto.response.PackageResponse;
import com.artoon.CourierManagementSystem.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer/package")
public class PackageController {

    @Autowired
    PackageService packageService;

    @GetMapping
    public ApiResponse<List<PackageResponse>> getMyPackages(Authentication authentication)
    {
        try {

            List<PackageResponse> packageResponses = packageService.getMyPackages(authentication);

            return new ApiResponse<>(true, "Packages retrieved successfully", packageResponses);
        } catch (Exception e) {
            return new ApiResponse<>(false, "Failed to retrieve packages: " + e.getMessage(), null);
        }
    }

    @GetMapping("get-by-id/{id}")
    public  PackageResponse getPackageById(@PathVariable Long id, Authentication authentication) {
        try {
            return packageService.getPackageById(id,authentication);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve package: " + e.getMessage());
        }
    }


}
