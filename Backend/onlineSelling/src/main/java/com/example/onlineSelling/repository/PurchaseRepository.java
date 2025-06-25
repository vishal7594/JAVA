package com.example.onlineSelling.repository;

import com.example.onlineSelling.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase,Long> {
}
