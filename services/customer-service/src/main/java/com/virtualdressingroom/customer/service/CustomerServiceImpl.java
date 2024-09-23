package com.virtualdressingroom.customer.service;

import com.virtualdressingroom.customer.dtos.CustomerRequest;
import com.virtualdressingroom.customer.dtos.CustomerResponse;
import com.virtualdressingroom.customer.entity.Customer;
import com.virtualdressingroom.customer.exception.CustomerAlreadyExistsException;
import com.virtualdressingroom.customer.exception.CustomerNotFoundException;
import com.virtualdressingroom.customer.repository.CustomerRepository;
import com.virtualdressingroom.customer.util.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper entityDtoMapping;

    public String createCustomer(CustomerRequest request) {

        if (customerRepository.existsByEmail(request.email())) {
            throw new CustomerAlreadyExistsException("Customer with email " + request.email() + " already exists");
        }

        Customer customer = entityDtoMapping.toCustomer(request);
        customer = this.customerRepository.save(customer);
        return customer.getId();

    }

    public void updateCustomer(CustomerRequest request) {
        var customer = this.customerRepository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException("Customer found with the provided ID:" + request.id()));

        // Updating fields of Customer
        customer.setFirstname(request.firstname());
        customer.setLastname(request.lastname());
        customer.setEmail(request.email());
        customer.setMobile(request.mobile());
        customer.setAddress(request.address());

        this.customerRepository.save(customer);
    }

    public List<CustomerResponse> findAllCustomers() {

        List<Customer> customers = customerRepository.findAll();

        return customers.stream().map(customer -> entityDtoMapping.toCustomerResponse(customer)).toList();
    }

    public CustomerResponse findById(String id) {

        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found with the provided ID:" + id));
        return entityDtoMapping.toCustomerResponse(customer);
    }

    public boolean existsById(String id) {
        return this.customerRepository.findById(id)
                .isPresent();
    }

    public void deleteCustomer(String id) {

        this.customerRepository.deleteById(id);
    }
}
