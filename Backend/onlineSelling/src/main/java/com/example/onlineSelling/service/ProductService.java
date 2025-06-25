package com.example.onlineSelling.service;

import com.example.onlineSelling.dto.ApiResponse;
import com.example.onlineSelling.dto.ProductRequest;
import com.example.onlineSelling.dto.ProductResponse;
import com.example.onlineSelling.model.Product;
import com.example.onlineSelling.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepo;

    @Autowired
    ModelMapper modelMapper;

    public ApiResponse<ProductResponse> addProduct(ProductRequest request) {

        ApiResponse<ProductResponse> response =null;
        try {
            Product product = modelMapper.map(request,Product.class);
            productRepo.save(product);

            ProductResponse productResponse = modelMapper.map(product,ProductResponse.class);
            response = new ApiResponse<>(true,"Add product successfully",productResponse);

        } catch (Exception e) {
            response = new ApiResponse<>(false,e.getMessage(),null);
        }
        return  response;
    }

    public ApiResponse<ProductResponse> updateProduct(long id,ProductRequest request) {
        ApiResponse<ProductResponse> response =null;
        try {
            Product product = productRepo.findById(id).orElse(null);

            if(product == null)  throw new Exception("product is not exist with this id");

              modelMapper.map(request,product);

              productRepo.save(product);

            ProductResponse productResponse = modelMapper.map(product,ProductResponse.class);
            response = new ApiResponse<>(true,"Update product successfully",productResponse);

        } catch (Exception e) {
            response = new ApiResponse<>(false,e.getMessage(),null);
        }
        return  response;
    }
}



















































