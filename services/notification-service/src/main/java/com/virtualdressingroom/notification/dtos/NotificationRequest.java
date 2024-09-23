package com.virtualdressingroom.notification.dtos;

public record NotificationRequest(
        String to,
        String subject,
        String message
) {
}
