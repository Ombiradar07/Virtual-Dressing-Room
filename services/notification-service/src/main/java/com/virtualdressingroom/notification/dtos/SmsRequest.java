package com.virtualdressingroom.notification.dtos;

public record SmsRequest(
        String to,
        String message
) {
}
