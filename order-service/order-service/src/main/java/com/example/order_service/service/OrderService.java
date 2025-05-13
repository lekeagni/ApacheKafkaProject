package com.example.order_service.service;

import com.example.order_service.event.OrderEvent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    public OrderEvent createOrder( OrderEvent orderEvent);

    public List<OrderEvent> getAllOrder();

    public OrderEvent updateOrder( int orderId, OrderEvent orderEvent);

    public Boolean delete(int orderId);
}
