package com.example.onlineSelling.service;

import com.example.onlineSelling.model.Customer;
import com.example.onlineSelling.model.Product;
import com.example.onlineSelling.model.Purchase;
import com.example.onlineSelling.model.PurchaseItem;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;


@Service
public class NotificationService {

    @Autowired
    MailService mailService;

    public void sendPurchaseNotificationWhatsApp(Customer customer, Purchase purchase) {
        String toWhatsApp = "whatsapp:" + customer.getMobileNumber(); // e.g., whatsapp:+91XXXXXXXXXX

        StringBuilder message = new StringBuilder();
        message.append("🧾 *Thank you, ").append(customer.getName()).append("!*\n")
                .append("Your purchase is confirmed.\n\n");

        for (PurchaseItem item : purchase.getItems()) {
            message.append("• *").append(item.getProduct().getName()).append("* × ")
                    .append(item.getQuantity())
                    .append(" @ ₹").append(item.getProduct().getPrice()).append("\n");
        }

        message.append("\n*GST:* ₹").append(purchase.getGstAmount())
                .append("\n*Total:* ₹").append(purchase.getTotalAmount())
                .append("\n\n🛍️ Visit Again!");

        Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:+917878188291"),
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                message.toString()
        ).create();

        mailService.sendSimpleTextMail("Purchase Details",message.toString());
    }

    public void sendPaymentFailedNoificaiton(Customer customer) {

        StringBuilder message = new StringBuilder();

        message.append("❌ *Payment Failed!*\n")
                .append("Dear ").append(customer.getName()).append(",\n")
                .append("Your purchase was not completed due to a payment failure.\n");
        // ✅ Send WhatsApp message via Twilio
        Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:+917878188291"),
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                message.toString()
        ).create();
    }

    @Value("${admin.whatsapp}")
    private String adminWhatsAppNumber;

    public void sendLowStockAlert(Product product) {
        StringBuilder message = new StringBuilder();

        message.append("⚠️ *Low Stock Alert!*\n")
                .append("Product: ").append(product.getName()).append("\n")
                .append("Current Stock: ").append(product.getStockCount()).append("\n")
                .append("Threshold: ").append(product.getThreshold()).append("\n")
                .append("🛒 Consider restocking soon.");

        Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:" + adminWhatsAppNumber),
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"), // Twilio Sandbox
                message.toString()
        ).create();
    }
}
