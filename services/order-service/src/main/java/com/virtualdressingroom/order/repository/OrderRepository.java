package com.virtualdressingroom.order.repository;

import com.virtualdressingroom.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
