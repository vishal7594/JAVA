package com.example.saleCampaign.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponse {
    private String id;
    private String title;
    private BigDecimal mrp;
    private BigDecimal currentPrice;
    private int inventory;
}
