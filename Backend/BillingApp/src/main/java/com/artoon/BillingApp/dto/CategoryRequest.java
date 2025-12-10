package com.artoon.BillingApp.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Builder
public class CategoryRequest {

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "bg_color")
    private  String bgColor;


}
