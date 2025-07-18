package com.artoon.CourierManagementSystem.model.entity;

import com.artoon.CourierManagementSystem.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private  Long id;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private  User customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Package> packages = new ArrayList<>();

    public Order(LocalDateTime orderDate, OrderStatus status, User customer) {
        this.orderDate = orderDate;
        this.status = status;
        this.customer = customer;
    }
}
