package com.virtualdressingroom.order.service;


import com.virtualdressingroom.order.dtos.OrderRequest;
import com.virtualdressingroom.order.dtos.OrderResponse;
import com.virtualdressingroom.order.entity.Order;
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

    @Transactional
    public Integer createOrder(OrderRequest orderRequest) {

        // TODO : Verify whether user exists


        // TODO : verify whether product exists

        // TODO : Place/save the order

        Order order = mapper.toOrder(orderRequest);
        order.setStatus(OrderStatus.CONFIRMED);
        order.setOrderDate(LocalDateTime.now());

        // TODO: Implement Payment process here before saving order

        Order savedOrder = orderRepository.save(order);
        return mapper.toOrderResponse(savedOrder).id();
    }

    @Override
    public List<OrderResponse> findAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(order -> mapper.toOrderResponse(order)).collect(Collectors.toList());
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
        Order updatedOrder = orderRepository.save(order);
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
        Order updatedOrder = orderRepository.save(order);
        return true;
    }


}
