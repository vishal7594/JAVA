package com.example.onlineSelling.service;

import com.example.onlineSelling.dto.ApiResponse;
import com.example.onlineSelling.dto.PurchaseRequest;
import com.example.onlineSelling.dto.PurchaseResponse;
import com.example.onlineSelling.model.Customer;
import com.example.onlineSelling.model.Product;
import com.example.onlineSelling.model.Purchase;
import com.example.onlineSelling.model.PurchaseItem;
import com.example.onlineSelling.repository.ProductRepository;
import com.example.onlineSelling.repository.PurchaseItemRepository;
import com.example.onlineSelling.repository.PurchaseRepository;
import com.example.onlineSelling.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepo;
    @Autowired
    PurchaseItemRepository purchaseItemRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    ProductRepository productRepo;

    @Autowired
    NotificationService notificationService;

    @Autowired
    MailService mailService;


    public ApiResponse<PurchaseResponse> createPurchase(PurchaseRequest request) {

        try {
            Customer customer = userRepo.findById(request.getCustomerId()).orElse(null);
            if(customer == null) throw new Exception("customer is not found with this id");

            Purchase purchase = new Purchase();
            purchase.setCustomer(customer);
            purchase.setPurchaseDate(LocalDateTime.now());

            List<PurchaseItem> purchaseItemList = new ArrayList<>();
            BigDecimal total = BigDecimal.valueOf(0);

            Map<Product, Integer> involvedProducts = new HashMap<>();

            for (PurchaseRequest.ItemRequest productItem : request.getItems())
            {
                Product product = productRepo.findById(productItem.getProductId()).orElse(null);
                if(product == null) throw new RuntimeException("Product is not found with this id");

                if(product.getStockCount() < productItem.getQuantity())
                {
                    throw new RuntimeException("Not enough stock for " + product.getName());
                }
                involvedProducts.put(product, productItem.getQuantity());

                // Create purchase item
                PurchaseItem item = new PurchaseItem(purchase,product,productItem.getQuantity());
                purchaseItemList.add(item);


                BigDecimal totalAmount = product.getPrice().multiply(BigDecimal.valueOf(productItem.getQuantity()));

                total = total.add(totalAmount);

            }

            BigDecimal gst = total.multiply(BigDecimal.valueOf(0.18));
            boolean isPaymentSuccess = makePayment(total.add(gst));

            if(!isPaymentSuccess)
            {
                notificationService.sendPaymentFailedNoificaiton(customer);
                return  new ApiResponse<>(false,"Payment failed. Purchase cancelled and stock reverted.",null);
            }

            for (Map.Entry<Product, Integer> entry : involvedProducts.entrySet()) {
                Product product = entry.getKey();
                int qty = entry.getValue();

                product.setStockCount(product.getStockCount() - qty);

                if (product.getStockCount() < product.getThreshold()) {
                    System.out.println("⚠️ Low stock warning for: " + product.getName());
                    notificationService.sendLowStockAlert(product);
                }
                productRepo.save(product);
            }


            purchase.setItems(purchaseItemList);
            purchase.setTotalAmount(total.add(gst));
            purchase.setGstAmount(gst);
            purchase.setPaid(true);

            purchase = purchaseRepo.save(purchase);

            purchaseItemRepo.saveAll(purchaseItemList);


            List<PurchaseResponse.ItemDetail> itemDetailList = new ArrayList<>();

            for (PurchaseItem  purchaseItem : purchaseItemList)
            {
                PurchaseResponse.ItemDetail item = new PurchaseResponse.ItemDetail(purchaseItem.getProduct().getName(),
                        purchaseItem.getQuantity(),purchaseItem.getProduct().getPrice());
                itemDetailList.add(item);
            }


            PurchaseResponse response = new PurchaseResponse(purchase.getId(),
                    customer.getName(),
                    purchase.getTotalAmount(),
                    purchase.getGstAmount(),
                    purchase.isPaid(),
                    itemDetailList);

            notificationService.sendPurchaseNotificationWhatsApp(customer, purchase);
            return new ApiResponse<>(true,"purchase done",response);


        } catch (Exception e) {
            return new ApiResponse<>(false,e.getMessage(),null);
        }
    }

    private  boolean makePayment(BigDecimal totalAmount)
    {
        return   new Random().nextBoolean();
    }
}

































