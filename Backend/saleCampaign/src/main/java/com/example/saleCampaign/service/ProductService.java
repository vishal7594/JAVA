package com.example.saleCampaign.service;

import com.example.saleCampaign.dto.request.ProductRequest;
import com.example.saleCampaign.dto.response.ProductResponse;
import com.example.saleCampaign.dto.response.ProductsResponse;
import com.example.saleCampaign.model.PriceHistory;
import com.example.saleCampaign.model.Product;
import com.example.saleCampaign.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    PriceHistoryService priceHistoryService;

    @Autowired
    ModelMapper modelMapper;

    public ProductResponse addProduct(ProductRequest request) throws RuntimeException{

        System.out.println("add product service called");
        Product product = modelMapper.map(request, Product.class);
        String randomId = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        product.setId(randomId);
        product.setCurrentPrice(request.getMrp());
        productRepository.save(product);
        return modelMapper.map(product, ProductResponse.class);
    }

    public List<ProductResponse> addProducts(List<ProductRequest> request) throws RuntimeException{

        System.out.println("add product service called");

        List<Product> products = new ArrayList<>();

        for(ProductRequest requestEntry : request) {
            Product product = modelMapper.map(requestEntry, Product.class);
            String randomId = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
            product.setId(randomId);
            product.setCurrentPrice(requestEntry.getMrp());
            System.out.println("Adding product with ID: " + product.getTitle());
            productRepository.save(product);
            products.add(product);
        }


        return products.stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
    }

    public  Product getProductById(String id) {
        return productRepository.findById(id).orElse(null);
    }

    public  void updateProductPrice(Product product, double currentPrice, double discount) {

        product.setCurrentPrice(currentPrice);
        productRepository.save(product);
        savePriceHistory(product, currentPrice, discount);
    }

    private void  savePriceHistory(Product product, double currentPrice, double discount) {
        PriceHistory priceHistory = new PriceHistory(product, currentPrice, discount, LocalDate.now());
        priceHistoryService.savePriceHistory(priceHistory);
    }

    public ProductsResponse getAllProducts(int page, int size) {
        Pageable pageable =  PageRequest.of(page,size);
        Page<Product> productPage = productRepository.findAll(pageable);

        List<ProductResponse> products = productPage.
                stream().
                map(product -> modelMapper.map(product,ProductResponse.class)).
                toList();

        ProductsResponse productsResponse = new ProductsResponse(products,
                productPage.getNumber(),
                productPage.getSize(),
                productPage.getNumberOfElements());
        return  productsResponse;
    }
}



































