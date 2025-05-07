package com.example.order_service.service;

import com.example.order_service.event.OrderEvent;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    public OrderEvent createOrder();
}
