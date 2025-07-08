package com.example.saleCampaign.controller;

import com.example.saleCampaign.dto.ApiResponse;
import com.example.saleCampaign.dto.request.CampaignRequest;
import com.example.saleCampaign.dto.response.CampaignResponse;
import com.example.saleCampaign.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin/campaign")
public class CampaignController {

    @Autowired
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

    @DeleteMapping("/delete-campaign/{campaignId}")
    public  ApiResponse<String> deleteCampaign(@PathVariable String campaignId) {
        ApiResponse<String> apiResponse;
        try {
            campaignService.deleteCampaign(campaignId);
            apiResponse = new ApiResponse<>(true, "Campaign deleted successfully", null);
        } catch (Exception e) {
            apiResponse = new ApiResponse<>(false, "Failed to delete campaign: " + e.getMessage(), null);
        }
        return apiResponse;
    }

}
