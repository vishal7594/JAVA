package com.artoon.CourierManagementSystem.repository;

import com.artoon.CourierManagementSystem.model.entity.Order;
import com.artoon.CourierManagementSystem.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByCustomer(User user);
}
