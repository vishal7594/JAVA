package com.artoon.CourierManagementSystem.service;

import com.artoon.CourierManagementSystem.enums.PackageStatus;
import com.artoon.CourierManagementSystem.model.dto.request.UpdateStatusRequest;
import com.artoon.CourierManagementSystem.model.dto.response.AssignmentResponse;
import com.artoon.CourierManagementSystem.model.dto.response.PaginatedResponse;
import com.artoon.CourierManagementSystem.model.entity.User;
import com.artoon.CourierManagementSystem.repository.UserRepository;
import com.artoon.CourierManagementSystem.util.AuthHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

    @Autowired
    PackageService packageService;

    @Autowired
    UserRepository userRepository;

    public PaginatedResponse<AssignmentResponse> getAllAssignments(int page, int size, Authentication authentication) {

        User agent = AuthHelper.getUserFromAuth(authentication,userRepository);
        System.out.println("Agent ID: " + agent.getId());
        return packageService.getAllAssignmentsById(page,size,agent.getId());
    }

    public void updateAssignmentStatus(UpdateStatusRequest request, Authentication authentication) {
        User agent = AuthHelper.getUserFromAuth(authentication,userRepository);
        packageService.updateAssignmentStatus(request.getAssignmentId(), request.getStatus(), agent.getId());
    }
}
