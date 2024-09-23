package com.virtualdressingroom.payment.dtos;


public record PaymentRequest(
        Integer orderId,
        double amount
) {}
