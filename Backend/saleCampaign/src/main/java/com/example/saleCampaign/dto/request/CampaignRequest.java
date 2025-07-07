package com.example.saleCampaign.dto.request;

import lombok.Data;

import java.util.List;

@Data
    public class CampaignRequest {
        private String startDate;
        private String endDate;
        private String title;
        private List<CampaignDiscountEntry> campaignDiscount;

        @Data
        public static class CampaignDiscountEntry {
            private String productId;
            private int discount;
        }
    }

