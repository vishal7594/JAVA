package com.example.saleCampaign.controller;

import com.example.saleCampaign.dto.ApiResponse;
import com.example.saleCampaign.dto.request.CampaignRequest;
import com.example.saleCampaign.dto.response.CampaignResponse;
import com.example.saleCampaign.service.CampaignService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin/campaign")
public class CampaignController {

    CampaignService campaignService;

    @PostMapping("/add-campaign")
    public ApiResponse<CampaignResponse> addCampaign(@RequestBody CampaignRequest request) {

        ApiResponse <CampaignResponse> apiResponse = null;

        try {
            CampaignResponse response  =  campaignService.addCampaign(request);
            apiResponse = new ApiResponse<>(true, "Campaign added successfully", response);

        } catch (Exception e) {
            apiResponse = new ApiResponse<>(false, "Failed to add campaign: " + e.getMessage(), null);
        }
        return apiResponse;
    }
}
