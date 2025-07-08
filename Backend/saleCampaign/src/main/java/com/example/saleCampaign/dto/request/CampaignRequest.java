package com.example.saleCampaign.dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
    public class CampaignRequest {
        private LocalDate startDate;
        private LocalDate endDate;
        private String title;
        private List<CampaignDiscountEntry> campaignDiscount;

        @Data
        public static class CampaignDiscountEntry {
            private String productId;
            private int discount;
        }
    }

