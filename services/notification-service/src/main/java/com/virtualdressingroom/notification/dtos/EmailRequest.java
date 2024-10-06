package com.virtualdressingroom.notification.dtos;

public record EmailRequest(
        String to,
        String subject,
        String message
) {
}
