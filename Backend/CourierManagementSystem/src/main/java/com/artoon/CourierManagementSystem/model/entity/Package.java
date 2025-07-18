package com.artoon.CourierManagementSystem.model.entity;

import com.artoon.CourierManagementSystem.enums.PackageStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "packages")
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String sourceAddress;
    private String destinationAddress;
    private String currentLocation;

    @Enumerated(EnumType.STRING)
    private PackageStatus status;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Package(String description, String sourceAddress, String destinationAddress, String currentLocation, PackageStatus status, Order order) {
        this.description = description;
        this.sourceAddress = sourceAddress;
        this.destinationAddress = destinationAddress;
        this.currentLocation = currentLocation;
        this.status = status;
        this.order = order;
    }
}
