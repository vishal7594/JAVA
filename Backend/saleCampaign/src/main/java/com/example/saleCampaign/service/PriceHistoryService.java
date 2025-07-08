package com.example.saleCampaign.service;

import com.example.saleCampaign.model.PriceHistory;
import com.example.saleCampaign.repository.PriceHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceHistoryService {

    @Autowired
    PriceHistoryRepository priceHistoryRepository;

    public  void  savePriceHistory(PriceHistory priceHistory)  throws RuntimeException
    {
        // Save the price history to the repository
        priceHistoryRepository.save(priceHistory);
    }
}
