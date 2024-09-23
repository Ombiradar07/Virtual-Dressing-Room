package com.virtualdressingroom.payment.service;


import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.virtualdressingroom.payment.Exception.handler.CustomPaymentException;
import com.virtualdressingroom.payment.dtos.PaymentRequest;
import com.virtualdressingroom.payment.dtos.PaymentResponse;
import com.virtualdressingroom.payment.entity.Payment;
import com.virtualdressingroom.payment.repository.PaymentRepository;
import com.virtualdressingroom.payment.utils.PaymentMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentServiceImpl implements PaymentService {

    private static final String CURRENCY = "INR";

    @Autowired
    private RazorpayClient razorpayClient;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        try {
            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", paymentRequest.amount() * 100); // Convert to paise
            orderRequest.put("currency", CURRENCY);
            orderRequest.put("receipt", paymentRequest.orderId().toString());

            Order createdOrder = razorpayClient.orders.create(orderRequest);

            Payment payment = paymentMapper.toPaymentEntity(paymentRequest);
            payment.setPaymentId(createdOrder.get("id").toString());
            payment.setPaymentStatus("created");
            payment.setCurrency(CURRENCY);
            payment.setPaymentDate(LocalDateTime.now());

            Payment savedPayment = paymentRepository.save(payment);

            return paymentMapper.toPaymentResponse(savedPayment);
        } catch (RazorpayException ex) {
            throw new CustomPaymentException("Failed to initiate payment: " + ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new CustomPaymentException("An error occurred while processing the payment: " + ex.getMessage(), ex);
        }
    }

    @Override
    public PaymentResponse getPaymentById(Integer id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new CustomPaymentException("Payment not found with ID: " + id));
        return paymentMapper.toPaymentResponse(payment);
    }
}





