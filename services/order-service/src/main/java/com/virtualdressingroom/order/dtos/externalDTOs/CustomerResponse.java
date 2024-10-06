package com.virtualdressingroom.order.dtos.externalDTOs;


public record CustomerResponse
        (
                String id,
                String firstname,
                String lastname,
                String email,
                String mobile
        ) {
}
