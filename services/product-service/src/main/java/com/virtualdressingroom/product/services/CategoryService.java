package com.virtualdressingroom.product.services;


import com.virtualdressingroom.product.dtos.categoryDtos.CategoryRequest;
import com.virtualdressingroom.product.dtos.categoryDtos.CategoryResponse;

import java.util.List;

public interface CategoryService {

    Integer createCategory(CategoryRequest request);

    void updateCategory(Integer categoryId, CategoryRequest request);

    void deleteCategory(Integer categoryId);

    CategoryResponse findById(Integer categoryId);

    List<CategoryResponse> findAll();
}
