package com.example.onlineSelling.repository;

import com.example.onlineSelling.model.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseItemRepository extends JpaRepository<PurchaseItem,Long> {
}
