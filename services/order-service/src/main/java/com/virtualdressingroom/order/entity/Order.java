package com.virtualdressingroom.order.entity;


import com.virtualdressingroom.order.utils.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Product ID cannot be null")
    @Positive(message = "Product ID must be positive")
    private Integer productId;

    @NotNull(message = "Customer ID cannot be null")
    @Positive(message = "Customer ID must be positive")
    private Integer customerId;

    @NotNull(message = "Quantity cannot be null")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be positive")
    private double price;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime orderDate;

    private String paymentId;

    @Pattern(regexp = "success|failed", message = "Payment status must be 'success' or 'failed'")
    private String paymentStatus;
}
