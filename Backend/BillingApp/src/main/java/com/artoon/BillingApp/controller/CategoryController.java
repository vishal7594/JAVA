package com.artoon.BillingApp.controller;

import com.artoon.BillingApp.dto.CategoryRequest;
import com.artoon.BillingApp.dto.CategoryResponse;
import com.artoon.BillingApp.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse addCategory(@RequestBody CategoryRequest request){
        return categoryService.add(request);
    }

    @GetMapping("/get-all")
    public List<CategoryResponse> getAllCategories() {
        return categoryService.read();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{categoryId}")
    public void deleteCategory(@PathVariable String categoryId){
        try {
            categoryService.delete(categoryId);
        }catch (Exception e)
        {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }
}

