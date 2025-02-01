package com.practice.order_service.service;

import com.practice.order_service.entity.OrderRequest;
import org.springframework.web.bind.annotation.PostMapping;

public interface OrderService {
 void createOrder(OrderRequest orderRequest);
}