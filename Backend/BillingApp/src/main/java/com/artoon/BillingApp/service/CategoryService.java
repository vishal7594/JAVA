package com.artoon.BillingApp.service;

import com.artoon.BillingApp.dto.CategoryRequest;
import com.artoon.BillingApp.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse add(CategoryRequest request);

    List<CategoryResponse> read();

    void delete(String categoryId);

}
