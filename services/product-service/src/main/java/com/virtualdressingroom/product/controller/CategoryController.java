package com.virtualdressingroom.product.controller;


import com.virtualdressingroom.product.dtos.categoryDtos.CategoryRequest;
import com.virtualdressingroom.product.dtos.categoryDtos.CategoryResponse;
import com.virtualdressingroom.product.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Integer> createCategory(@RequestBody @Valid CategoryRequest request) {
        return ResponseEntity.ok(categoryService.createCategory(request));
    }

    @PutMapping("/{category-id}")
    public ResponseEntity<Void> updateCategory(@PathVariable("category-id") Integer categoryId,
                                               @RequestBody @Valid CategoryRequest request) {
        categoryService.updateCategory(categoryId, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{category-id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("category-id") Integer categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{category-id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable("category-id") Integer categoryId) {
        return ResponseEntity.ok(categoryService.findById(categoryId));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }
}
