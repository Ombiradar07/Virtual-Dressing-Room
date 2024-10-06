package com.virtualdressingroom.order.dtos.externalDTOs;


public record EmailRequest
        (
                String to,
                String subject,
                String message
        ) {
}
