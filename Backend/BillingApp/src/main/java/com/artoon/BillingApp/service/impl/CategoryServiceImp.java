package com.artoon.BillingApp.service.impl;

import com.artoon.BillingApp.dto.CategoryRequest;
import com.artoon.BillingApp.dto.CategoryResponse;
import com.artoon.BillingApp.entity.CategoryEntity;
import com.artoon.BillingApp.repository.CategoryRepository;
import com.artoon.BillingApp.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepository categoryRepo;
    @Override
    public CategoryResponse add(CategoryRequest request) {

        CategoryEntity category = toCategoryEntity(request);

       category =  categoryRepo.save(category);

       return  toCategoryResponse(category);
    }

    @Override
    public List<CategoryResponse> read() {

        List<CategoryEntity> categories = categoryRepo.findAll();

        return categories.stream()
                .map(categoryEntity -> toCategoryResponse(categoryEntity))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String categoryId) throws  RuntimeException {

        CategoryEntity existCategory = categoryRepo.findByCategoryId(categoryId).orElseThrow(()-> new RuntimeException("Category not found" + categoryId));
        categoryRepo.delete(existCategory);
    }

    private  CategoryEntity  toCategoryEntity(CategoryRequest request)
    {
        return   CategoryEntity.builder()
                .categoryId(UUID.randomUUID().toString())
                .name(request.getName())
                .description(request.getDescription())
                .bgColor(request.getBgColor())
                .build();
    }

    private  CategoryResponse toCategoryResponse(CategoryEntity categoryEntity)
    {
        return  CategoryResponse.builder()
                .categoryId(categoryEntity.getCategoryId())
                .name(categoryEntity.getName())
                .description(categoryEntity.getDescription())
                .bgColor(categoryEntity.getBgColor())
                .imgUrl(categoryEntity.getImgUrl())
                .createdAt(categoryEntity.getCreatedAt())
                .updatedAt(categoryEntity.getUpdatedAt())
                .build();
    }
}
