package com.virtualdressingroom.customer.dtos;

import com.virtualdressingroom.customer.util.Address;


public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email,
        String mobile,
        Address address
) {

}
