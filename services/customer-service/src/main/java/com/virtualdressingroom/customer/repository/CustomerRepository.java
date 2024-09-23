package com.virtualdressingroom.customer.repository;

import com.virtualdressingroom.customer.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String > {

     Boolean existsByEmail(String email);

}
