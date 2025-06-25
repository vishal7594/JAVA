package com.example.onlineSelling.service;

import com.example.onlineSelling.model.Product;
import com.example.onlineSelling.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class StockReportScheduler {
    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private MailService mailService;

    @Scheduled(cron = "30 05 19 * * ?") // Every day at 8:00 PM
    public void sendDailyStockEmailReport() throws IOException {
        System.out.println("sendDailyStockEmailReport starting" );
        List<Product> products = productRepo.findAll();
        File csvFile = mailService.generateStockReportCsv(products);
        mailService.sendStockCsvAttachment(csvFile);
        System.out.println("sendDailyStockEmailReport sending" );
    }
}
