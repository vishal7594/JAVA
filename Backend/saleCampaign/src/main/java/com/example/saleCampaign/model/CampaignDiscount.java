package com.example.saleCampaign.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CampaignDiscount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private  Campaign campaign;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private  Product product;

    private double discount;

    public CampaignDiscount(Campaign campaign, Product product, double discount) {
        this.campaign = campaign;
        this.product = product;
        this.discount = discount;
    }
}
