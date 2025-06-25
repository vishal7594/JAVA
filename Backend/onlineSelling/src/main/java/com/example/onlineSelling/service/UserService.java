package com.example.onlineSelling.service;

import com.example.onlineSelling.dto.ApiResponse;
import com.example.onlineSelling.dto.UserRequest;
import com.example.onlineSelling.dto.UserResponse;
import com.example.onlineSelling.model.Customer;
import com.example.onlineSelling.repository.UserRepository;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepo;

    @Autowired
    ModelMapper modelMapper;

    public ApiResponse<UserResponse> addCustomer(UserRequest request) {

        try {
            Customer customer = modelMapper.map(request,Customer.class);
            userRepo.save(customer);
            return  new ApiResponse<>(true,"added",modelMapper.map(customer,UserResponse.class));

        } catch (Exception e) {
            return new ApiResponse<>(false,e.getMessage(),null);
        }
    }
}
