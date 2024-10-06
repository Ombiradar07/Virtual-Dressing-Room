package com.virtualdressingroom.order.dtos.externalDTOs;


public record ProductPurchaseResponse
        (
                Integer productId,
                Integer quantity,
                double price
        ) {
}
