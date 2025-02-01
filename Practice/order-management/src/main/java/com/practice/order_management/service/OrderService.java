package com.practice.order_management.service;

import com.practice.order_management.entity.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    Order createOrder(Order order);
    List<Order> getAllOrders();
    Order getOrderById(Long orderId);
    Order updateOrder(Order order, Long id);

}
