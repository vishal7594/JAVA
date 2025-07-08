package com.example.saleCampaign.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {
    private String title;
    private double mrp;
    private int inventory;
}
