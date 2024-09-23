package com.virtualdressingroom.payment.repository;

import com.virtualdressingroom.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {}
