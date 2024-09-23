package com.virtualdressingroom.product.dtos.productDtos;

import java.math.BigDecimal;

public record ProductPurchaseResponse(

        Integer productId,

        String name,

        String description,

        BigDecimal price,

        Integer quantity
) {
}
