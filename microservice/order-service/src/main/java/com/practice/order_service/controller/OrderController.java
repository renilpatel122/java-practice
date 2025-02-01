package com.practice.order_service.controller;

import com.practice.order_service.entity.OrderRequest;
import com.practice.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public String createOrder(@RequestBody OrderRequest orderRequest) {
    orderService.createOrder(orderRequest);
        return "Order Created";
    }




}
