package com.virtualdressingroom.customer.util;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Validated
public class Address {

    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}
