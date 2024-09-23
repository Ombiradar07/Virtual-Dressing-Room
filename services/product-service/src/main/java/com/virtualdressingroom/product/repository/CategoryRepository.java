package com.virtualdressingroom.product.repository;

import com.virtualdressingroom.product.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
