package com.example.onlineSelling.controller;

import com.example.onlineSelling.dto.ApiResponse;
import com.example.onlineSelling.dto.ProductRequest;
import com.example.onlineSelling.dto.ProductResponse;
import com.example.onlineSelling.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin/product")
public class StockController {

    @Autowired
    ProductService productService;


    @PostMapping("/add-product")
    public ApiResponse<ProductResponse> addProduct(@RequestBody ProductRequest request) {
        return productService.addProduct(request);
    }

    @PutMapping("/update-product/{id}")
    public ApiResponse<ProductResponse> updateProduct(@PathVariable long id,@RequestBody ProductRequest request) {
        return productService.updateProduct(id,request);
    }
}
