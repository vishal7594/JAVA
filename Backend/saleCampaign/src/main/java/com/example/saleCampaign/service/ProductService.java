package com.example.saleCampaign.service;

import com.example.saleCampaign.dto.request.ProductRequest;
import com.example.saleCampaign.dto.response.ProductResponse;
import com.example.saleCampaign.model.Product;
import com.example.saleCampaign.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ModelMapper modelMapper;

    public ProductResponse addProduct(ProductRequest request) throws RuntimeException{

        Product product = modelMapper.map(request, Product.class);
        String randomId = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        product.setId(randomId);
        productRepository.save(product);
        return modelMapper.map(product, ProductResponse.class);
    }
}
