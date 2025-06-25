package com.example.onlineSelling.dto;

import com.example.onlineSelling.model.Customer;
import lombok.Data;

import java.util.List;
@Data
public class PurchaseRequest {
    private Long customerId;
    private List<ItemRequest> items;

    @Data
    public static class ItemRequest {
        private Long productId;
        private int quantity;
    }
}
