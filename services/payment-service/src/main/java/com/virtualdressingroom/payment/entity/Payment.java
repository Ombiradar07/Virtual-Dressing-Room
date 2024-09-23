package com.virtualdressingroom.payment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Order ID cannot be null")
    @Column(name = "order_id", nullable = false)
    private Integer orderId;

    @NotNull(message = "Payment ID cannot be null")
    private String paymentId;

    @NotNull(message = "Payment Status cannot be null")
    @Pattern(regexp = "created|failed", message = "Payment status must be 'created' or 'failed'")
    private String paymentStatus;

    @NotNull(message = "Amount cannot be null")
    private double amount;

    @NotNull(message = "Currency cannot be null")
    private String currency;

    private LocalDateTime paymentDate;
}


