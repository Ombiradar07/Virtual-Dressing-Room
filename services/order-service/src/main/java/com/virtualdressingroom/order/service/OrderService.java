package com.virtualdressingroom.order.service;


import com.virtualdressingroom.order.dtos.OrderRequest;
import com.virtualdressingroom.order.dtos.OrderResponse;
import com.virtualdressingroom.order.utils.OrderStatus;

import java.util.List;

public interface OrderService {

    Integer createOrder(OrderRequest orderRequest);

    List<OrderResponse> findAllOrders();

    OrderResponse findById(Integer id);

    Boolean cancelOrder(Integer id);

    Boolean changeOrderStatus(Integer id, OrderStatus newStatus);
}
