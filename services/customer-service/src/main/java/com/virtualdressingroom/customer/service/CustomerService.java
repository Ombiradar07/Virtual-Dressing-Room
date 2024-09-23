package com.virtualdressingroom.customer.service;

import com.virtualdressingroom.customer.dtos.CustomerRequest;
import com.virtualdressingroom.customer.dtos.CustomerResponse;

import java.util.List;

public interface CustomerService {

    void updateCustomer(CustomerRequest request);

    List<CustomerResponse> findAllCustomers();

    CustomerResponse findById(String id);

    boolean existsById(String id);

    void deleteCustomer(String id);
}
