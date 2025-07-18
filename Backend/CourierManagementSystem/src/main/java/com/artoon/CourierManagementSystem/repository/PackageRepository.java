package com.artoon.CourierManagementSystem.repository;

import com.artoon.CourierManagementSystem.model.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageRepository extends JpaRepository<Package, Long> {
    @Query("SELECT p FROM Package p WHERE p.order.customer.username = :username")
    List<com.artoon.CourierManagementSystem.model.entity.Package> findAllByCustomerUsername(String username);
}
