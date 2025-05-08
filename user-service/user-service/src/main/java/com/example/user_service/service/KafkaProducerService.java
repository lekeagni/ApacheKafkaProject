package com.example.user_service.service;

import com.example.user_service.event.UserEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, UserEvent> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, UserEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;

    }

    public void SendUserEvent(UserEvent userEvent){
        kafkaTemplate.send("user-events",userEvent);
    }
}
