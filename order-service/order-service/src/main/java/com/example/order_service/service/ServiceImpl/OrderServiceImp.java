package com.example.order_service.service.ServiceImpl;

import com.example.order_service.event.OrderEvent;
import com.example.order_service.service.OrderService;

import java.util.List;

public class OrderServiceImp implements OrderService {
    @Override
    public OrderEvent createOrder(int productId, int userId, OrderEvent orderEvent) {
        return null;
    }

    @Override
    public List<OrderEvent> getAllOrder() {
        return List.of();
    }

    @Override
    public OrderEvent updateOrder(int orderId, OrderEvent orderEvent) {
        return null;
    }

    @Override
    public Boolean delete(int orderId) {
        return null;
    }
}
