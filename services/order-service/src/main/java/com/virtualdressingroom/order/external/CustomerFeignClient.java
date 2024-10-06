package com.virtualdressingroom.order.external;


import com.virtualdressingroom.order.dtos.externalDTOs.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service")
public interface CustomerFeignClient {

    @GetMapping("/api/v1/customers/{id}")
    CustomerResponse getCustomerById(@PathVariable("id") String id);
}
