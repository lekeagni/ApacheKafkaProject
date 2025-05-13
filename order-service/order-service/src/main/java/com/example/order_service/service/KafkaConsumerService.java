package com.example.order_service.service;

import com.example.order_service.event.ProductEvent;
import com.example.order_service.event.UserEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class KafkaConsumerService {

    private final Map<Integer, UserEvent> validUsers = new ConcurrentHashMap<>();
    private final Map<Integer, ProductEvent> availableProducts = new ConcurrentHashMap<>();

    @KafkaListener(
            topics = "user-events",
            groupId = "order-group",
            containerFactory = "userKafkaListenerContainerFactory"
    )
    public void consumeUser(UserEvent userEvent) {
        System.out.println(" Reçu depuis 'user-events': " + userEvent);
        validUsers.put(userEvent.getUserId(), userEvent);
    }

    @KafkaListener(
            topics = "product-events",
            groupId = "order-group",
            containerFactory = "productKafkaListenerContainerFactory"
    )
    public void consumeProduct(ProductEvent productEvent) {
        System.out.println("Reçu depuis 'product-events': " + productEvent);
        availableProducts.put(productEvent.getProductId(), productEvent);
    }

    public boolean isUserValid(int userId) {
        return validUsers.containsKey(userId);
    }

    public boolean isProductAvailable(int productId, int requestedQuantity) {
        ProductEvent product = availableProducts.get(productId);
        return product != null && product.getQuantity() >= requestedQuantity;
    }
}
