package com.example.saleCampaign.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Campaign {
    @Id
    private String id;

    private String title;

    private LocalDate startDate;

    private LocalDate endDate;
}
