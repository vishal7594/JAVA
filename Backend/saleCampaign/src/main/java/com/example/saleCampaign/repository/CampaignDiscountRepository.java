package com.example.saleCampaign.repository;

import com.example.saleCampaign.model.CampaignDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignDiscountRepository extends JpaRepository<CampaignDiscount, Long> {

    List<CampaignDiscount> findByCampaignId(String id);
}
