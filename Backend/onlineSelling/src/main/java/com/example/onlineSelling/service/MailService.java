package com.example.onlineSelling.service;

import com.example.onlineSelling.model.Product;
import com.example.onlineSelling.repository.ProductRepository;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Service
public class MailService {
    @Value("${admin.email}")
    private String adminEmail;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    ProductRepository productRepo;

    public void sendSimpleTextMail(String subject, String message) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(adminEmail);
        mail.setSubject(subject);
        mail.setText(message);
        javaMailSender.send(mail);
    }

    public void sendStockCsvAttachment(File csvFile) {
        try {

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(adminEmail);
            helper.setSubject("📦 Daily Stock Report (CSV)");
            helper.setText("Attached is your daily stock report in CSV format.\n\n- OnlineSelling Bot");

            FileSystemResource file = new FileSystemResource(csvFile);
            helper.addAttachment("StockReport.csv", file);

            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace(); // Use proper logging in production
        }
    }

    public File generateStockReportCsv(List<Product> products) throws IOException {

        File file = File.createTempFile("StockReport-", ".csv");
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println("Product Name,Stock Count,Threshold");
            for (Product p : products) {
                writer.printf("%s,%d,%d%n", p.getName(), p.getStockCount(), p.getThreshold());
            }
        }
        return file;
    }
}
