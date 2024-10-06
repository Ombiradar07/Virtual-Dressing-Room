package com.virtualdressingroom.order.service;

import com.virtualdressingroom.order.dtos.OrderRequest;
import com.virtualdressingroom.order.dtos.OrderResponse;
import com.virtualdressingroom.order.dtos.externalDTOs.*;
import com.virtualdressingroom.order.entity.Order;
import com.virtualdressingroom.order.external.CustomerFeignClient;
import com.virtualdressingroom.order.external.NotificationFeignClient;
import com.virtualdressingroom.order.external.PaymentFeignClient;
import com.virtualdressingroom.order.external.ProductFeignClient;
import com.virtualdressingroom.order.repository.OrderRepository;
import com.virtualdressingroom.order.utils.OrderMapper;
import com.virtualdressingroom.order.utils.OrderStatus;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper mapper;

    private final CustomerFeignClient customerFeignClient;
    private final ProductFeignClient productFeignClient;
    private final PaymentFeignClient paymentFeignClient;
    private final NotificationFeignClient notificationFeignClient;

    @Transactional
    public Integer createOrder(OrderRequest orderRequest) {

        // 1. Verify whether the user exists
        CustomerResponse customer = customerFeignClient.getCustomerById(orderRequest.customerId());
        if (customer == null) {
            throw new EntityNotFoundException("Customer not found with ID: " + orderRequest.customerId());
        }

        // 2. Verify whether the product exists and is available
        ProductPurchaseResponse product = productFeignClient.purchaseProduct(
                new ProductPurchaseRequest(orderRequest.productId(), orderRequest.quantity()));
        if (product == null || product.quantity() <= 0) {
            throw new EntityNotFoundException("Product not available.");
        }

        // 3. Create Order (place/save the order)
        Order order = mapper.toOrder(orderRequest);
        order.setStatus(OrderStatus.CONFIRMED);
        order.setOrderDate(LocalDateTime.now());

        // Save the Order to generate the orderId
        Order savedOrder = orderRepository.save(order);

        // 4. Process Payment using the generated orderId
        PaymentResponse paymentResponse = paymentFeignClient.createPayment(
                new PaymentRequest(savedOrder.getId(), product.price())); // Use savedOrder.getId()

        if (!paymentResponse.paymentStatus().equalsIgnoreCase("Created")) {

            throw new IllegalStateException("Payment failed.");
        }

        // 5. Send notifications to the user via email and SMS
        EmailRequest emailRequest = new EmailRequest(
                customer.email(),
                "Your Order Has Been Confirmed!",
                "Dear " + customer.firstname()+" " +customer.lastname() + ",\n\n" +
                        "Thank you for your order! We're excited to inform you that your payment has been completed successfully. " +
                        "Your order with Order ID: " + savedOrder.getId() + " has been successfully placed and is now being processed.\n\n" +
                        "If you have any questions or need assistance, feel free to reach out to our support team.\n\n" +
                        "Best regards,\n" +
                        "The Virtual Dressing Room Team"
        );



        notificationFeignClient.sendEmail(emailRequest);

       SmsRequest smsRequest = new SmsRequest(
                customer.mobile(),
                "Dear " + customer.firstname()+" "+ customer.lastname() + ",\n\n" +
                        "Thank you for your order! We're excited to inform you that your payment has been completed successfully. " +
                        "Your order with Order ID: " + savedOrder.getId() + " has been successfully placed and is now being processed.\n\n" +
                        "If you have any questions or need assistance, feel free to reach out to our support team.\n\n" +
                        "Best regards,\n" +
                        "The Virtual Dressing Room Team"
        );
        notificationFeignClient.sendSms(smsRequest);

        return mapper.toOrderResponse(savedOrder).id();
    }


    @Override
    public List<OrderResponse> findAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(mapper::toOrderResponse).collect(Collectors.toList());
    }

    public OrderResponse findById(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id " + id));
        return mapper.toOrderResponse(order);
    }

    @Transactional
    public Boolean cancelOrder(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id " + id));

        if (order.getStatus() == OrderStatus.CANCELLED) {
            throw new IllegalStateException("Order is already cancelled.");
        }

        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
        return true;
    }

    @Transactional
    public Boolean changeOrderStatus(Integer id, OrderStatus newStatus) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id " + id));

        if (order.getStatus() == OrderStatus.CANCELLED) {
            throw new IllegalStateException("Cannot change the status of a cancelled order.");
        }

        order.setStatus(newStatus);
        orderRepository.save(order);
        return true;
    }
}
