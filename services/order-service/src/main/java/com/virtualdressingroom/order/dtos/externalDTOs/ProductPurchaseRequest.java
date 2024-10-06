package com.virtualdressingroom.order.dtos.externalDTOs;


public record ProductPurchaseRequest
        (
                Integer productId,
                Integer quantity
        ) {
}
