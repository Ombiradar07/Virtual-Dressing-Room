package com.virtualdressingroom.customer.controller;

import com.virtualdressingroom.customer.dtos.CustomerRequest;
import com.virtualdressingroom.customer.dtos.CustomerResponse;
import com.virtualdressingroom.customer.service.CustomerServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerServiceImpl service;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return new ResponseEntity<>(service.createCustomer(request), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequest request) {
        this.service.updateCustomer(request);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll() {
        return new ResponseEntity<>(this.service.findAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/exists/{customer-id}")
    public ResponseEntity<Boolean> existsById(@PathVariable("customer-id") String customerId) {
        return new ResponseEntity<>(this.service.existsById(customerId), HttpStatus.OK);
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable("customer-id") String customerId) {
        return new ResponseEntity<>(this.service.findById(customerId), HttpStatus.OK);
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> delete(@PathVariable("customer-id") String customerId) {
        this.service.deleteCustomer(customerId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
