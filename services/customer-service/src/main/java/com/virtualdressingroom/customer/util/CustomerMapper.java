package com.virtualdressingroom.customer.util;

import com.virtualdressingroom.customer.dtos.CustomerRequest;
import com.virtualdressingroom.customer.dtos.CustomerResponse;
import com.virtualdressingroom.customer.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {


    // Convert CustomerRequest to Customer
    public Customer toCustomer(CustomerRequest request) {
        if (request == null) {
            return null;
        }
        return new Customer(
                request.id(),
                request.firstname(),
                request.lastname(),
                request.email(),
                request.mobile(),
                request.address()
        );
    }

    // Convert Customer to CustomerResponse
    public CustomerResponse toCustomerResponse(Customer customer) {
        if (customer == null) {
            return null;
        }
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getMobile(),
                customer.getAddress()
        );
    }
}


