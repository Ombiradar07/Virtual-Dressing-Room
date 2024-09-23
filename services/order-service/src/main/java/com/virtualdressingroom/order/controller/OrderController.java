package com.virtualdressingroom.order.controller;

import com.virtualdressingroom.order.dtos.OrderRequest;
import com.virtualdressingroom.order.dtos.OrderResponse;
import com.virtualdressingroom.order.service.OrderServiceImpl;
import com.virtualdressingroom.order.utils.OrderStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceImpl orderService;

    @PostMapping
    public ResponseEntity<Integer> createOrder(@RequestBody @Valid OrderRequest request) {
        return ResponseEntity.ok(this.orderService.createOrder(request));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll() {
        return ResponseEntity.ok(this.orderService.findAllOrders());
    }

    @GetMapping("/{order-id}")
    public ResponseEntity<OrderResponse> findById(@PathVariable("order-id") Integer orderId) {
        return ResponseEntity.ok(this.orderService.findById(orderId));
    }

    @PutMapping("/cancel/{order-id}")
    public ResponseEntity<Boolean> cancelOrder(@PathVariable("order-id") Integer orderId) {
        Boolean b = orderService.cancelOrder(orderId);
        return new ResponseEntity<>(b, HttpStatus.OK);
    }

    @PutMapping("/change-status/{order-id}/{new-status}")
     public ResponseEntity<Boolean> changeOrderStatus(@PathVariable("order-id") Integer orderId, @PathVariable("new-status") OrderStatus newStatus) {
         Boolean b = orderService.changeOrderStatus(orderId, newStatus);
          return new ResponseEntity<>(b, HttpStatus.OK);
    }
}
