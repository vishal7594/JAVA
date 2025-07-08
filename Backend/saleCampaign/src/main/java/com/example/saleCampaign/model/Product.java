package com.example.saleCampaign.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class Product {
    @Id
    private String id;

    private String title;

    private Double mrp;

    @Column(name = "current_price")
    private Double currentPrice;

    private int inventory;
}
