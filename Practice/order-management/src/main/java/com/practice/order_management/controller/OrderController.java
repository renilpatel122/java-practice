package com.practice.order_management.controller;


import com.practice.order_management.entity.Order;
import com.practice.order_management.service.OrderService;
import com.practice.order_management.utill.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<Order>> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.ok(ApiResponse.success(createdOrder, "Order created successfully"));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping
    public  ResponseEntity<ApiResponse<List<Order>>> getAllOrder() {
        return ResponseEntity.ok(ApiResponse.success(orderService.getAllOrders(), "Order created successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Order>> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.ok(ApiResponse.success(createdOrder, "Order created successfully"));
    }

//    @PreAuthorize("hasAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Order>> getOrder(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(ApiResponse.success(order, "Order retrieved successfully"));
    }
}

