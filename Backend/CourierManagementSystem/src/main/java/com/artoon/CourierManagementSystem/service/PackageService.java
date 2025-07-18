package com.artoon.CourierManagementSystem.service;

import com.artoon.CourierManagementSystem.enums.PackageStatus;
import com.artoon.CourierManagementSystem.enums.Role;
import com.artoon.CourierManagementSystem.model.dto.request.AssignPackageRequest;
import com.artoon.CourierManagementSystem.model.dto.response.AssignmentResponse;
import com.artoon.CourierManagementSystem.model.dto.response.PackageResponse;
import com.artoon.CourierManagementSystem.model.dto.response.PaginatedResponse;
import com.artoon.CourierManagementSystem.model.entity.DeliveryAssignment;
import com.artoon.CourierManagementSystem.model.entity.Package;
import com.artoon.CourierManagementSystem.model.entity.User;
import com.artoon.CourierManagementSystem.repository.DeliveryAssignmentRepository;
import com.artoon.CourierManagementSystem.repository.PackageRepository;
import com.artoon.CourierManagementSystem.repository.UserRepository;
import com.artoon.CourierManagementSystem.util.AuthHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.query.JSqlParserUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PackageService {

    @Autowired
    PackageRepository packageRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    DeliveryAssignmentRepository assignmentRepository;

    public List<PackageResponse> getMyPackages(Authentication authentication) throws RuntimeException {

        User user = AuthHelper.getUserFromAuth(authentication, userRepository);

        List<Package> packages = packageRepository.findAllByCustomerUsername(user.getUsername());

        System.out.println("Packages for user: " + user.getUsername() + " - Count: " + packages.size());

        List<PackageResponse> packageResponses = packages.stream().map(pkg -> modelMapper.map(pkg, PackageResponse.class)).toList();

        return packageResponses;
    }


    public PackageResponse getPackageById(Long id, Authentication authentication) {
        User user = AuthHelper.getUserFromAuth(authentication, userRepository);
        Package pkg = packageRepository.findById(id).orElse(null);
        if (pkg == null) {
            throw new RuntimeException("Package not found");
        }

        if (!user.getId().equals(pkg.getOrder().getId())) {
            throw new RuntimeException("You are not authorized to view this package");
        }

        return modelMapper.map(pkg, PackageResponse.class);
    }

    public void assignPackageToAgent(AssignPackageRequest request) throws RuntimeException {

        User agent = userRepository.findById(request.getAgentId()).orElse(null);

        if (!agent.getRole().equals(Role.DELIVERY_AGENT)) {
            throw new RuntimeException("User with ID " + request.getAgentId() + " is not a delivery agent");
        }

        for (Long packageIds : request.getPackageIds()) {
            Package pkg = packageRepository.findById(packageIds).orElse(null);
            if (pkg == null) {
                throw new RuntimeException("Package with ID " + packageIds + " not found");
            }

            if (!pkg.getStatus().equals(PackageStatus.PENDING)) {
                throw new RuntimeException("Package with ID " + packageIds + " is not in PENDING status");
            }

           boolean alreadyAssigned = assignmentRepository.existsByPkg(pkg);
            if(alreadyAssigned) {
                throw new RuntimeException("Package with ID " + packageIds + " is already assigned to an agent");
            }

            pkg.setStatus(PackageStatus.IN_TRANSIT);
            packageRepository.save(pkg);

            DeliveryAssignment assignment = new DeliveryAssignment(agent, pkg, LocalDateTime.now());

            assignmentRepository.save(assignment);
        }
    }

    private  PaginatedResponse<AssignmentResponse> prepareAssignmentRespose( Page<DeliveryAssignment> assignments)
    {
        List<AssignmentResponse> assignmentResponses = new ArrayList<>();

        for(DeliveryAssignment  delivery : assignments.getContent()) {

            PackageResponse pkgResponse = modelMapper.map(delivery.getPkg(), PackageResponse.class);

            AssignmentResponse assignmentResponse = new AssignmentResponse(delivery.getId(),
                    pkgResponse,
                    delivery.getDeliveryAgent().getId(),
                    delivery.getDeliveryAgent().getUsername(),
                    delivery.getAssignedAt());

            assignmentResponses.add(assignmentResponse);
        }

        return  new PaginatedResponse<AssignmentResponse>(assignmentResponses,
                assignments.getNumber(),
                assignments.getSize(),
                assignments.getNumberOfElements(),
                assignments.getTotalElements(),
                assignments.getTotalPages());
    }

    public PaginatedResponse<AssignmentResponse> getAllAssignments(int page, int size) throws  RuntimeException {

        Pageable pageable =  PageRequest.of(page,size, Sort.by(Sort.Direction.DESC, "assignedAt"));
        Page<DeliveryAssignment> assignments = assignmentRepository.findAll(pageable);
        return  prepareAssignmentRespose(assignments);
    }

    public PaginatedResponse<AssignmentResponse> getAllAssignmentsById(int page, int size,Long id) throws  RuntimeException {

        Pageable pageable =  PageRequest.of(page,size, Sort.by(Sort.Direction.DESC, "assignedAt"));
        Page<DeliveryAssignment> assignments = assignmentRepository.findByDeliveryAgentId(id,pageable);
        return  prepareAssignmentRespose(assignments);
    }


    public void updateAssignmentStatus(Long assignmentId, PackageStatus status, Long id) {
        DeliveryAssignment assignment = assignmentRepository.findById(assignmentId).orElse(null);
        if (assignment == null) {
            throw new RuntimeException("Assignment with ID " + assignmentId + " not found");
        }

        if (!assignment.getDeliveryAgent().getId().equals(id)) {
            throw new RuntimeException("You are not authorized to update this assignment");
        }

        Package pkg = assignment.getPkg();
        pkg.setStatus(status);
        packageRepository.save(pkg);
    }
}











































