package com.artoon.CourierManagementSystem.service;

import com.artoon.CourierManagementSystem.enums.OrderStatus;
import com.artoon.CourierManagementSystem.enums.PackageStatus;
import com.artoon.CourierManagementSystem.model.dto.request.OrderRequest;
import com.artoon.CourierManagementSystem.model.dto.response.OrderResponse;
import com.artoon.CourierManagementSystem.model.entity.Order;
import com.artoon.CourierManagementSystem.model.entity.Package;
import com.artoon.CourierManagementSystem.model.entity.User;
import com.artoon.CourierManagementSystem.repository.OrderRepository;
import com.artoon.CourierManagementSystem.repository.UserRepository;
import com.artoon.CourierManagementSystem.util.AuthHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.artoon.CourierManagementSystem.util.AuthHelper.getUserFromAuth;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository; // Assuming you have an OrderRepository to handle database operations

    @Autowired
    UserRepository userRepository;

    public OrderResponse placeOrder(Authentication authentication, OrderRequest orderRequest) throws  RuntimeException {

        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        List<Package> packages = new ArrayList<>();

        Order order = new Order(LocalDateTime.now(), OrderStatus.PLACED,user);

        for(OrderRequest.PackageRequest pkgModel : orderRequest.getPackages() )
        {
            Package pkg = new Package(pkgModel.getDescription(),
                    pkgModel.getSourceAddress(),
                    pkgModel.getDestinationAddress(),
                    pkgModel.getSourceAddress(),
                    PackageStatus.PENDING,
                    order
                    );
            packages.add(pkg);
        }

        order.setPackages(packages);
        order =  orderRepository.save(order);
        return  new OrderResponse(order.getId(),order.getOrderDate(), order.getStatus());
    }

    public OrderResponse getOrderStatus(Long orderId, Authentication authentication) throws  RuntimeException {

        User user = AuthHelper.getUserFromAuth(authentication, userRepository);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!order.getCustomer().getId().equals(user.getId())) {
            throw new RuntimeException("You do not have permission to view this order");
        }

        return new OrderResponse(order.getId(), order.getOrderDate(), order.getStatus());
    }

    public List<OrderResponse> getMyOrders(Authentication authentication) throws  RuntimeException {

        User user = AuthHelper.getUserFromAuth(authentication, userRepository);

        List<Order> orders = orderRepository.findByCustomer(user);
        List<OrderResponse> orderResponses = new ArrayList<>();

        for (Order order : orders) {
            orderResponses.add(new OrderResponse(order.getId(), order.getOrderDate(), order.getStatus()));
        }

        return orderResponses;
    }
}
