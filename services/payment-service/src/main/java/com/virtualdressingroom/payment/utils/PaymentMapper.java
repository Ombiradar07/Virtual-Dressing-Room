package com.virtualdressingroom.payment.utils;

import com.virtualdressingroom.payment.dtos.PaymentRequest;
import com.virtualdressingroom.payment.dtos.PaymentResponse;
import com.virtualdressingroom.payment.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public Payment toPaymentEntity(PaymentRequest paymentRequest) {
        return Payment.builder()
                .orderId(paymentRequest.orderId())
                .amount(paymentRequest.amount())
                .build();
    }

    public PaymentResponse toPaymentResponse(Payment payment) {
        return new PaymentResponse(
                payment.getId(),
                payment.getOrderId(),
                payment.getPaymentId(),
                payment.getPaymentStatus(),
                payment.getAmount(),
                payment.getCurrency(),
                payment.getPaymentDate()
        );
    }
}
