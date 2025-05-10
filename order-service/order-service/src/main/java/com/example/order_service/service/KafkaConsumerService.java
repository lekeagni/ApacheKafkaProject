package com.example.order_service.service;

import com.example.order_service.event.ProductEvent;
import com.example.order_service.event.UserEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(
            topics = "user-events",
            groupId = "order-group",
            containerFactory = "userKafkaListenerContainerFactory"
    )
    public void consumeUser(UserEvent userEvent) {
        System.out.println("📥 Reçu depuis 'user-events' : " + userEvent);
    }

    @KafkaListener(
            topics = "product-events",
            groupId = "order-group",
            containerFactory = "productKafkaListenerContainerFactory"
    )
    public void consumeProduct(ProductEvent productEvent) {
        System.out.println("📥 Reçu depuis 'product-events' : " + productEvent);
        // Logique métier à ajouter ici
    }
}

