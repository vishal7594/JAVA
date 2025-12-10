package com.artoon.BillingApp.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Data
@Builder
public class CategoryResponse {
    private  String categoryId;
    private String name;
    private String description;
    private  String bgColor;
    private String imgUrl;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
