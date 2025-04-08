//package com.practice.order_management.config;
//
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class RabbitMQConsumer {
//
//    @RabbitListener(queues = "order.queue")
//    public void receiveMessage(String message) {
//        System.out.println("Received from RabbitMQ: " + message);
//    }
//}
//
