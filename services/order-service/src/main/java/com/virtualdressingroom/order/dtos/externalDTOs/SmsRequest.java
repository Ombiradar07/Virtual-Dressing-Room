package com.virtualdressingroom.order.dtos.externalDTOs;

public record SmsRequest(
        String to,
        String message
) {
}
