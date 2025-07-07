package com.example.saleCampaign.service;

import com.example.saleCampaign.dto.request.CampaignRequest;
import com.example.saleCampaign.dto.response.CampaignResponse;
import com.example.saleCampaign.model.Campaign;
import com.example.saleCampaign.repository.CampaignRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CampaignService {

    @Autowired
    CampaignRepository campaignRepository;

    @Autowired
    ModelMapper modelMapper;

    public CampaignResponse addCampaign(CampaignRequest request) throws RuntimeException {

        Campaign campaign = modelMapper.map(request, Campaign.class);

        // Generate a random ID for the campaign
        String randomId = java.util.UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        campaign.setId(randomId);
        // Save the campaign to the repository
        campaignRepository.save(campaign);

        return modelMapper.map(campaign, CampaignResponse.class);
    }

}
