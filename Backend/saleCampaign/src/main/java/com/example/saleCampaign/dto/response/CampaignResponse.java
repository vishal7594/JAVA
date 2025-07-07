package com.example.saleCampaign.dto.response;

import com.example.saleCampaign.dto.request.CampaignRequest;
import lombok.Data;

import java.util.List;

public class CampaignResponse {
    private String id;
    private String startDate;
    private String endDate;
    private String title;
    private List<CampaignRequest.CampaignDiscountEntry> campaignDiscount;

    @Data
    public static class CampaignDiscountEntry {
        private String productId;
        private int discount;
    }
}
