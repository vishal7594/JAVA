package com.example.saleCampaign.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductsResponse {
    private List<ProductResponse> products;
    private int pageIndex;
    private int pageSize;
    private long numberOfElements;
}
