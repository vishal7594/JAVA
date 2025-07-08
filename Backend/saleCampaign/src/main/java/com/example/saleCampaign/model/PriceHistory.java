package com.example.saleCampaign.model;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "pricing_history")
public class PriceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private  Product product;

    private double price;
    private double discount;
    private LocalDate date;

    public PriceHistory(Product product, double price, double discount, LocalDate date) {
        this.product = product;
        this.price = price;
        this.discount = discount;
        this.date = date;
    }
}
