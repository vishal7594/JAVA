package com.example.onlineSelling.controller;

import com.example.onlineSelling.dto.ApiResponse;
import com.example.onlineSelling.dto.PurchaseRequest;
import com.example.onlineSelling.dto.PurchaseResponse;
import com.example.onlineSelling.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/purchase")
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    @PostMapping()
    public ApiResponse<PurchaseResponse> createPurchase(@RequestBody PurchaseRequest request)
    {
        return  purchaseService.createPurchase(request);
    }
}
