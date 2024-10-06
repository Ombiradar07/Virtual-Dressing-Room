package com.virtualdressingroom.order.dtos;


import com.virtualdressingroom.order.utils.OrderStatus;

import java.time.LocalDateTime;

public record OrderResponse(
        Integer id,

        Integer productId,

        String customerId,

        Integer quantity,

        double price,

        OrderStatus status,

        LocalDateTime orderDate,

        String paymentId,

        String paymentStatus
) {
}
