package com.example.onlineSelling.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer stockCount;
    private Integer threshold = 5; // Default threshold

    public Product(String name, BigDecimal price, Integer stockCount, Integer threshold) {
        this.name = name;
        this.price = price;
        this.stockCount = stockCount;
        this.threshold = threshold;
    }
}
