package com.practice.order_management.config;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "order.topic", groupId = "order_group")
    public void listen(ConsumerRecord<String, String> record) {
        System.out.println("Consumed message: " + record.value());
    }
}

