package com.example.saleCampaign.controller;

import com.example.saleCampaign.dto.ApiResponse;
import com.example.saleCampaign.dto.request.ProductRequest;
import com.example.saleCampaign.dto.response.ProductResponse;
import com.example.saleCampaign.dto.response.ProductsResponse;
import com.example.saleCampaign.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/product")
public class ProductController {

    @Autowired
   ProductService productService;

    @PostMapping("/add-product")
    public ApiResponse<ProductResponse> addProduct(@RequestBody ProductRequest request) {

        System.out.println("add product request");
        ApiResponse<ProductResponse> response = null;
        try {

            ProductResponse productResponse = productService.addProduct(request);

            response = new ApiResponse<>(true,"add product successfully", productResponse);
        } catch (Exception ex) {
            response = new ApiResponse<>(false, ex.getMessage(), null);
        }
        return response;
    }

    @PostMapping("/add-products")
    public ApiResponse<List<ProductResponse>> addProducts(@RequestBody List<ProductRequest> request) {

        System.out.println("add product request");
        ApiResponse<List<ProductResponse>> response = null;
        try {
            List<ProductResponse> productResponse = productService.addProducts(request);
            response = new ApiResponse<>(true,"add product successfully", productResponse);
        } catch (Exception ex) {
            response = new ApiResponse<>(false, ex.getMessage(), null);
        }
        return response;
    }

    @GetMapping("/get-products/{page}/{size}")
    public  ApiResponse<ProductsResponse>  getAllProducts(@PathVariable int page, @PathVariable int size) {

        System.out.println("get all products request");
        // Assuming pagination is handled in the service layer
        ApiResponse<ProductsResponse> response = null;
        try {
            ProductsResponse products = productService.getAllProducts(page, size);
            response = new ApiResponse<>(true, "Fetched all products successfully", products);
        } catch (Exception ex) {
            response = new ApiResponse<>(false, ex.getMessage(), null);
        }
        return response;
    }
}
