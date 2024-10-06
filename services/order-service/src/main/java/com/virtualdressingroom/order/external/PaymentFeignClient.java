package com.virtualdressingroom.order.external;


import com.virtualdressingroom.order.dtos.externalDTOs.PaymentRequest;
import com.virtualdressingroom.order.dtos.externalDTOs.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service")
public interface PaymentFeignClient {

    @PostMapping("/api/v1/payments")
    PaymentResponse createPayment(@RequestBody PaymentRequest paymentRequest);
}
