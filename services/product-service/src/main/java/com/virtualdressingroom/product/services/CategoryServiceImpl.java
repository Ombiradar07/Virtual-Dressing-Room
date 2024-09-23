package com.virtualdressingroom.product.services;


import com.virtualdressingroom.product.dtos.categoryDtos.CategoryRequest;
import com.virtualdressingroom.product.dtos.categoryDtos.CategoryResponse;
import com.virtualdressingroom.product.entity.Category;
import com.virtualdressingroom.product.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Override
    @Transactional
    public Integer createCategory(CategoryRequest request) {
        Category category = Category.builder()
                .name(request.name())
                .description(request.description())
                .build();
        return repository.save(category).getId();
    }

    @Override
    @Transactional
    public void updateCategory(Integer categoryId, CategoryRequest request) {
        Category category = repository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.setName(request.name());
        category.setDescription(request.description());
        repository.save(category);
    }

    @Override
    @Transactional
    public void deleteCategory(Integer categoryId) {
        if (!repository.existsById(categoryId)) {
            throw new RuntimeException("Category not found");
        }
        repository.deleteById(categoryId);
    }

    @Override
    public CategoryResponse findById(Integer categoryId) {
        Category category = repository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return new CategoryResponse(category.getId(), category.getName(), category.getDescription());
    }

    @Override
    public List<CategoryResponse> findAll() {
        return repository.findAll().stream()
                .map(category -> new CategoryResponse(category.getId(), category.getName(), category.getDescription()))
                .collect(Collectors.toList());
    }
}
