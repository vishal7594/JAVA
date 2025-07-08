package com.example.saleCampaign.service;

import com.example.saleCampaign.model.CampaignDiscount;
import com.example.saleCampaign.repository.CampaignDiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignDiscountService {

    @Autowired
    CampaignDiscountRepository cdRepository;

    public void saveAllCampaignDiscounts(List<CampaignDiscount> campaignDiscountList) {
        cdRepository.saveAll(campaignDiscountList);
    }

    public List<CampaignDiscount> findByCampaignId(String id) {
        return cdRepository.findByCampaignId(id);
    }
}
