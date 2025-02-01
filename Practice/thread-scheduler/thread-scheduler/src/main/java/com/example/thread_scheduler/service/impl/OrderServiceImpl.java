package com.example.thread_scheduler.service.impl;

import com.example.thread_scheduler.ThreadSchedulerApplication;
import com.example.thread_scheduler.entity.Order;
import com.example.thread_scheduler.repository.OrderRepository;
import com.example.thread_scheduler.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    private final ExecutorService executorService;

    @Autowired
    private ApplicationContext applicationContext;  // Get the current application context

    @Autowired
    private WebServerApplicationContext context;  // Spring Boot context to trigger restart



    public OrderServiceImpl(ExecutorService executorService) {
        this.executorService = Executors.newFixedThreadPool(2);
    }


    @Override
    public Order createOrder(Order order) {
        order.setStatus("PENDING");
        return orderRepository.save(order);
    }

//    public void startLoggingOrders() {
//        // Create and start the logging thread
//        OrderLoggingThread loggingThread = new OrderLoggingThread(orderRepository);
//        loggingThread.start();
//    }

    public void fetchAndLogOrders() {
        List<Order> orders = orderRepository.findAll();

        // Log each order
        orders.forEach(order -> {
            System.out.println("Order ID: " + order.getId() + ", Customer ID: " + order.getCustomerId() + ", Status: " + order.getStatus());
        });
    }

    public void startLoggingOrdersPeriodically() {
        Runnable task = new Runnable() {
            @Override
            public void run() {

                fetchAndLogOrders();  // Fetch and log orders

                restartApplication();

            }
        };

        // Submit the task to the thread pool
        executorService.submit(task);
    }

    private void restartApplication() {
        try {
            // Stop and restart the Spring Boot application programmatically
            SpringApplication.exit(applicationContext);  // Exit the current application
            SpringApplication.run(ThreadSchedulerApplication.class);  // Start a new instance
            System.out.println("Application Restarted after logging all orders.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error during application restart: " + e.getMessage());
        }
    }

//    private void restartApplication() {
//        try {
//            // Restart the application by invoking the restart endpoint via context
//            context.getWebServer().stop();
//            context.getWebServer().start();
//            System.out.println("Application Restarted after logging all orders.");
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Error during application restart: " + e.getMessage());
//        }
//    }



    public void shutdownExecutor() {
        executorService.shutdown();
    }


}
