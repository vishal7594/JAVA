package com.artoon.CourierManagementSystem.model.dto.response;

import com.artoon.CourierManagementSystem.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Data
public class OrderResponse {
    private Long orderId;
    private LocalDateTime orderDate;
    private OrderStatus status;
}
