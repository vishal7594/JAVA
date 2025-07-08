package com.example.saleCampaign.repository;

import com.example.saleCampaign.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, String> {

    List<Campaign> findByStartDate(LocalDate today);

    List<Campaign> findByEndDate(LocalDate today);
}
