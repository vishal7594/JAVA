package com.artoon.CourierManagementSystem.controller.agent;

import com.artoon.CourierManagementSystem.model.dto.request.UpdateStatusRequest;
import com.artoon.CourierManagementSystem.model.dto.response.ApiResponse;
import com.artoon.CourierManagementSystem.model.dto.response.AssignmentResponse;
import com.artoon.CourierManagementSystem.model.dto.response.PaginatedResponse;
import com.artoon.CourierManagementSystem.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery-agent")
public class AgentController {

    @Autowired
    AgentService agentService;

    @GetMapping("/assignments/{page}/{size}")
    public ApiResponse<PaginatedResponse<AssignmentResponse>> getAllAssignments(@PathVariable int page, @PathVariable int size,
                                                                                Authentication authentication) {
        try {
            System.out.println("Get all packages request received");
            PaginatedResponse<AssignmentResponse> response = agentService.getAllAssignments(page, size,authentication);
            return new ApiResponse<>(true, "Fetched all packages successfully", response);
        } catch (Exception e) {
            return new ApiResponse<>(false, "Failed to fetch packages: " + e.getMessage(), null);
        }
    }

    @PostMapping("/assignment/update-status")
    public ApiResponse<String> updateAssignmentStatus(@RequestBody UpdateStatusRequest request, Authentication authentication)
    {
        try {
            System.out.println("Update assignment status request received");
            agentService.updateAssignmentStatus(request, authentication);
            return new ApiResponse<>(true, "Assignment status updated successfully", null);
        } catch (Exception e) {
            return new ApiResponse<>(false, "Failed to update assignment status: " + e.getMessage(), null);
        }
    }
}






































