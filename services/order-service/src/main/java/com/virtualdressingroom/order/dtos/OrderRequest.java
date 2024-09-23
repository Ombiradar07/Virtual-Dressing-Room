package com.virtualdressingroom.order.dtos;

public record OrderRequest(

        Integer productId,


        Integer customerId,


        Integer quantity,


        double price

) {
}
