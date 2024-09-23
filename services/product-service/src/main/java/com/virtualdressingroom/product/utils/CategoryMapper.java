package com.virtualdressingroom.product.utils;


import com.virtualdressingroom.product.entity.Category;
import com.virtualdressingroom.product.dtos.categoryDtos.CategoryRequest;
import com.virtualdressingroom.product.dtos.categoryDtos.CategoryResponse;

public class CategoryMapper {

    // Convert Category to CategoryResponse
    public static CategoryResponse toCategoryResponse(Category category) {
        if (category == null) {
            return null;
        }
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }

    // Convert CategoryRequest to Category
    public static Category toCategory(CategoryRequest categoryRequest) {
        if (categoryRequest == null) {
            return null;
        }
        return Category.builder()
                .name(categoryRequest.name())
                .description(categoryRequest.description())
                .build();
    }
}
