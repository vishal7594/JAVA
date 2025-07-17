package com.artoon.CourierManagementSystem.controller.customer;

import com.artoon.CourierManagementSystem.model.dto.request.OrderRequest;
import com.artoon.CourierManagementSystem.model.dto.response.ApiResponse;
import com.artoon.CourierManagementSystem.model.dto.response.OrderResponse;
import com.artoon.CourierManagementSystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer/order")
public class OrderController {

    @Autowired
    OrderService orderService;


    @PostMapping("/order-place")
    public ApiResponse<OrderResponse> placeOrder(OrderRequest orderRequest, Authentication authentication) {

        try {
            OrderResponse orderResponse = orderService.placeOrder(authentication, orderRequest);
            return new ApiResponse<>(true, "Order placed successfully", orderResponse);
        } catch (Exception e) {
            return new ApiResponse<>(false, "Order placement failed: " + e.getMessage(), null);
        }
    }

    @GetMapping("order-status/{orderId}")
    public ApiResponse<OrderResponse> getOrderStatus(@PathVariable Long orderId, Authentication authentication) {
        try {
            OrderResponse orderResponse = orderService.getOrderStatus(orderId, authentication);
            return new ApiResponse<>(true, "Order status retrieved successfully", orderResponse);
        } catch (Exception e) {
            return new ApiResponse<>(false, "Failed to retrieve order status: " + e.getMessage(), null);
        }
    }

    @GetMapping("/get-my-orders")
    public ApiResponse<List<OrderResponse>> getMyOrders(Authentication authentication) {
        try {
            List<OrderResponse> orderResponses = orderService.getMyOrders(authentication);
            return new ApiResponse<>(true, "Orders retrieved successfully", orderResponses);
        } catch (Exception e) {
            return new ApiResponse<>(false, "Failed to retrieve orders: " + e.getMessage(), null);
        }
    }
}
