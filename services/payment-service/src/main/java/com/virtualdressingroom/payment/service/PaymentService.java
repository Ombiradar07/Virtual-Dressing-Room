package com.virtualdressingroom.payment.service;


import com.virtualdressingroom.payment.dtos.PaymentRequest;
import com.virtualdressingroom.payment.dtos.PaymentResponse;


public interface PaymentService {
    PaymentResponse createPayment(PaymentRequest paymentRequest) throws Exception;
    PaymentResponse getPaymentById(Integer id);
}
