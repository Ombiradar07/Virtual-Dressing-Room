package com.virtualdressingroom.order.utils;


import com.virtualdressingroom.order.dtos.OrderRequest;
import com.virtualdressingroom.order.dtos.OrderResponse;
import com.virtualdressingroom.order.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    // Convert Order entity to OrderResponse record
    public OrderResponse toOrderResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getProductId(),
                order.getCustomerId(),
                order.getQuantity(),
                order.getPrice(),
                order.getStatus(),
                order.getOrderDate(),
                order.getPaymentId(),
                order.getPaymentStatus()
        );
    }

    // Convert OrderRequest record to Order entity
    public Order toOrder(OrderRequest orderRequest) {
        return new Order(
                null,
                orderRequest.productId(),
                orderRequest.customerId(),
                orderRequest.quantity(),
                orderRequest.price(),
                null,
                null,
                null, // paymentId initially null
                null // paymentStatus initially null
        );
    }

}

