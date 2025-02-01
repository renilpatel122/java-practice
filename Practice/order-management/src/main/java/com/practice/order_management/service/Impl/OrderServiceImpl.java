package com.practice.order_management.service.Impl;

import com.practice.order_management.entity.Order;
import com.practice.order_management.exception.OrderNotFoundException;
import com.practice.order_management.repository.OrderRepository;
import com.practice.order_management.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);


    @Autowired
    private OrderRepository orderRepository;
//    private final RabbitTemplate rabbitTemplate;
//    private final KafkaTemplate<String, String> kafkaTemplate;

//    @Autowired
//    public OrderServiceImpl(OrderRepository orderRepository,
//                            RabbitTemplate rabbitTemplate,
//                            KafkaTemplate<String, String> kafkaTemplate) {
//        this.orderRepository = orderRepository;
//        this.rabbitTemplate = rabbitTemplate;
//        this.kafkaTemplate = kafkaTemplate;
//    }

    @Override
    public Order createOrder(Order order) {
        order.setStatus("PENDING");
        Order savedOrder = orderRepository.save(order);

        String notificationMessage = "New Order Created: " + savedOrder.getId();
//        rabbitTemplate.convertAndSend("order.queue", notificationMessage);
//        kafkaTemplate.send("order.topic", notificationMessage);
        // Publish to RabbitMQ
        try {
//            rabbitTemplate.convertAndSend("order.queue", notificationMessage);
            logger.info("Message sent to RabbitMQ: {}", notificationMessage);
        } catch (Exception e) {
            logger.error("Failed to send message to RabbitMQ: {}", e.getMessage());
        }

        // Publish to Kafka
        try {
//            kafkaTemplate.send("order.topic", notificationMessage);
        } catch (Exception e) {
            logger.error("Error publishing to Kafka: {}", e.getMessage());
        }


        return savedOrder;

    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId.toString()));
    }

    @Override
    public Order updateOrder(Order order, Long id) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id.toString()));

        existingOrder.setCustomerId(order.getCustomerId());
        existingOrder.setStatus(order.getStatus());
        existingOrder.setItems(order.getItems());

        return orderRepository.save(existingOrder);

    }
}
