package com.example.onlineSelling.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class PurchaseResponse {
    private Long purchaseId;
    private String customerName;
    private BigDecimal totalAmount;
    private BigDecimal gstAmount;
    private boolean paid;
    private List<ItemDetail> items;

    @AllArgsConstructor
    @Data
    public static class ItemDetail {
        private String productName;
        private int quantity;
        private BigDecimal price;
    }
}
