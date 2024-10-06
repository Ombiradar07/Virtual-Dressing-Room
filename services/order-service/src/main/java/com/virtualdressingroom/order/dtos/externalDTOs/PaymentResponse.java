package com.virtualdressingroom.order.dtos.externalDTOs;

import java.time.LocalDateTime;

public record PaymentResponse
        (
                Integer id,
                Integer orderId,
                String paymentId,
                String paymentStatus,
                double amount,
                String currency,
                LocalDateTime paymentDate
        ) {
}
