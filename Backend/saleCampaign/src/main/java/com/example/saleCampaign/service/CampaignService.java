package com.example.saleCampaign.service;

import com.example.saleCampaign.dto.request.CampaignRequest;
import com.example.saleCampaign.dto.response.CampaignResponse;
import com.example.saleCampaign.model.Campaign;
import com.example.saleCampaign.model.CampaignDiscount;
import com.example.saleCampaign.model.Product;
import com.example.saleCampaign.repository.CampaignRepository;
import org.apache.coyote.Request;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CampaignService {

    @Autowired
    CampaignRepository campaignRepository;

    @Autowired
    ProductService productService;

    @Autowired
    CampaignDiscountService cdService;

    @Autowired
    ModelMapper modelMapper;

    public CampaignResponse addCampaign(CampaignRequest request) throws RuntimeException {

        Campaign campaign = modelMapper.map(request, Campaign.class);
        // Generate a random ID for the campaign
        String randomId = java.util.UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        campaign.setId(randomId);
        // Save the campaign to the repository
        campaignRepository.save(campaign);


        List<CampaignDiscount> campaignDiscountList = new ArrayList<>();

        for(CampaignRequest.CampaignDiscountEntry entry : request.getCampaignDiscount()) {
            Product product = productService.getProductById(entry.getProductId());
            if(product == null)
            {
                throw new RuntimeException("Product with ID " + entry.getProductId() + " not found");
            }
            CampaignDiscount campaignDiscount = new CampaignDiscount(campaign,product, entry.getDiscount());

            campaignDiscountList.add(campaignDiscount);
        }
        cdService.saveAllCampaignDiscounts(campaignDiscountList);
        CampaignResponse response =  modelMapper.map(request, CampaignResponse.class);
        response.setId(campaign.getId());
        return  response;
    }

    private void applyDiscounts(Campaign campaign) {

        List<CampaignDiscount> cdList =  cdService.findByCampaignId(campaign.getId());

        if(cdList.size() == 0)
        {
            System.out.println("No product list found for campaign with ID: " + campaign.getId());
            return;
        }

        for(CampaignDiscount cd : cdList) {

            Product product = cd.getProduct();
            double mrp = product.getMrp();

            double discountedPrice = mrp - (mrp * cd.getDiscount() / 100);
            // Log the pricing history (if applicable)

            productService.updateProductPrice(product, discountedPrice, cd.getDiscount());

        }
    }

    private  void  removeDiscounts(Campaign campaign)
    {
        List<CampaignDiscount> cdList =  cdService.findByCampaignId(campaign.getId());
        if(cdList.size() == 0)
        {
            System.out.println("No product list found for campaign with ID: " + campaign.getId());
            return;
        }

        for(CampaignDiscount cd : cdList) {

            Product product = cd.getProduct();
            double originalPrice = product.getMrp();
            productService.updateProductPrice(product, originalPrice, cd.getDiscount());
        }
    }

    @Scheduled(cron = "15 14 10 * * *")
    private  void  checkAndApplyDiscounts() {
        System.out.println("Checking and applying discounts for active campaigns...");
        LocalDate today = LocalDate.now();
        List<Campaign> activeCampaigns = campaignRepository.findByStartDate(today);
        for (Campaign campaign : activeCampaigns) {
            applyDiscounts(campaign);
        }
        List<Campaign> endingCampaigns = campaignRepository.findByEndDate(today);
        for (Campaign campaign : endingCampaigns) {
            removeDiscounts(campaign);
        }

    }


    public void deleteCampaign(String campaignId) throws  RuntimeException {
        Campaign campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new RuntimeException("Campaign not found with id: " + campaignId));
        campaignRepository.delete(campaign);
    }
}
