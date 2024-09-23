package com.virtualdressingroom.customer.dtos;

import com.virtualdressingroom.customer.util.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CustomerRequest(

        String id,

        @NotNull(message = "Customer firstname is required")
        String firstname,

        @NotNull(message = "Customer firstname is required")
        String lastname,

        @NotNull(message = "Customer Email is required")
        @Email(message = "Customer Email is not a valid email address")
        String email,

        @NotNull(message = "Customer mobile is required")
        @Pattern(regexp = "^[0-9]{10}$", message = "Customer mobile is not a valid mobile number")
        String mobile,

        Address address
) {

}
