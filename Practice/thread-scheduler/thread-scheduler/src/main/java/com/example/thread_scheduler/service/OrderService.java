package com.example.thread_scheduler.service;

import com.example.thread_scheduler.entity.Order;

public interface OrderService {
    Order createOrder(Order order);

//    void startLoggingOrders();

    void startLoggingOrdersPeriodically();

    void fetchAndLogOrders();

    void shutdownExecutor();
}
