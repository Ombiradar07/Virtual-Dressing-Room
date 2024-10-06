package com.virtualdressingroom.order.dtos.externalDTOs;

public record PaymentRequest
        (
                Integer orderId,
                double amount
        ) {
}
