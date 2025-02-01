package com.example.thread_scheduler.controller;

import com.example.thread_scheduler.entity.Order;
import com.example.thread_scheduler.repository.OrderRepository;
import com.example.thread_scheduler.service.OrderService;
import com.example.thread_scheduler.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping
    public ResponseEntity<ApiResponse<Order>> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.ok(ApiResponse.success(createdOrder, "Order created successfully"));
    }

//    @Scheduled(fixedRate = 5000)
    public void logAllOrders(List<Order> orders ) {
        orders.forEach(order -> {
            System.out.println("Order ID: " + order.getId() + ", Customer ID: " + order.getCustomerId() + ", Status: " + order.getStatus());
        });
    }


}
