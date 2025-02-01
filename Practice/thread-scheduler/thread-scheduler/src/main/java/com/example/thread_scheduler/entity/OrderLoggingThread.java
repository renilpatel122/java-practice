//package com.example.thread_scheduler.entity;
//
//import com.example.thread_scheduler.service.OrderService;
//
//public class OrderLoggingThread extends Thread {
//
//    private OrderService orderService;
//
//    public OrderLoggingThread(OrderService orderService) {
//        this.orderService = orderService;
//    }
//
//    @Override
//    public void run() {
//        while (true) {
//            try {
//                orderService.fetchAndLogOrders();
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
