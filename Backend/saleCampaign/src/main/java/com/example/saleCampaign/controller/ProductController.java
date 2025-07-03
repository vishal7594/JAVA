package com.example.saleCampaign.controller;

import com.example.saleCampaign.dto.ApiResponse;
import com.example.saleCampaign.dto.request.ProductRequest;
import com.example.saleCampaign.dto.response.ProductResponse;
import com.example.saleCampaign.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin/product")
public class ProductController {

    @Autowired
   ProductService productService;

    @PostMapping("/add-product")
    public ApiResponse<ProductResponse> addProduct(@RequestBody ProductRequest request) {

        ApiResponse<ProductResponse> response = null;
        try {

            ProductResponse productResponse = productService.addProduct(request);

            response = new ApiResponse<>(true,"add product successfully", productResponse);
        } catch (Exception ex) {
            response = new ApiResponse<>(false, ex.getMessage(), null);
        }
        return response;
    }
}
