package com.virtualdressingroom.product.dtos.categoryDtos;


import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
        @NotBlank(message = "Name is mandatory")
        String name,

        String description
) {
}
