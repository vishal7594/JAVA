package com.artoon.CourierManagementSystem.repository;

import com.artoon.CourierManagementSystem.model.entity.DeliveryAssignment;
import com.artoon.CourierManagementSystem.model.entity.Package;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryAssignmentRepository extends JpaRepository<DeliveryAssignment,Long> {

    boolean existsByPkg(Package pkg);

    Page<DeliveryAssignment> findByDeliveryAgentId(Long deliveryAgentId,Pageable pageable);
}
