package com.artoon.CourierManagementSystem.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "delivery_assignments")
public class DeliveryAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "delevery_agent_id")
    private  User deliveryAgent;

    @ManyToOne
    @JoinColumn(name = "package_id")
    private  Package pkg;

    private LocalDateTime assignedAt;

    public DeliveryAssignment(User deliveryAgent, Package pkg, LocalDateTime assignedAt) {
        this.deliveryAgent = deliveryAgent;
        this.pkg = pkg;
        this.assignedAt = assignedAt;
    }
}
