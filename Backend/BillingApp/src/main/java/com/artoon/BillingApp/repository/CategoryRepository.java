package com.artoon.BillingApp.repository;

import com.artoon.BillingApp.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    void deleteByCategoryId(String categoryId);

    Optional<CategoryEntity> findByCategoryId(String categoryId);
}
