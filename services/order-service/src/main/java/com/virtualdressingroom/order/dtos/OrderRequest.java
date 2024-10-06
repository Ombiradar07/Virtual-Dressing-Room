package com.virtualdressingroom.order.dtos;

public record OrderRequest(

        Integer productId,


        String customerId,


        Integer quantity,


        double price

) {
}
