package com.example.product_service.service;

import com.example.product_service.event.ProductEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String,ProductEvent> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, ProductEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void SendProductEvent(ProductEvent productEvent){
        kafkaTemplate.send("product-events", productEvent);
    }
}
