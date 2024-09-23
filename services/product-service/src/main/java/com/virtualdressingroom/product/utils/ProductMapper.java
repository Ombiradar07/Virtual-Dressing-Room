package com.virtualdressingroom.product.utils;

import com.virtualdressingroom.product.dtos.productDtos.ProductPurchaseResponse;
import com.virtualdressingroom.product.dtos.productDtos.ProductRequest;
import com.virtualdressingroom.product.dtos.productDtos.ProductResponse;
import com.virtualdressingroom.product.entity.Category;
import com.virtualdressingroom.product.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {


    public Product toProduct(ProductRequest request) {
        return Product.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .availableQuantity(request.availableQuantity())
                .price(request.price())
                .category(
                        Category.builder()
                                .id(request.categoryId())
                                .build()
                )
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }

    public ProductPurchaseResponse productToProductPurchaseResponse(Product product, Integer quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );
    }
}
