package com.example.order_service.service;

import com.example.order_service.event.OrderEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @Value("${kafka.topic.order-events}")
    private String orderEventsTopic;

    public KafkaProducerService(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderEvent(OrderEvent orderEvent) {
        kafkaTemplate.send(orderEventsTopic, String.valueOf(orderEvent.getOrderId()), orderEvent);
        System.out.println("📤 OrderEvent envoyé sur le topic '" + orderEventsTopic + "' : " + orderEvent);
    }
}
