package com.example.order_service.service.ServiceImpl;

import com.example.order_service.event.OrderEvent;
import com.example.order_service.exception.OrderNotFoundException;
import com.example.order_service.exception.ProductNotFoundException;
import com.example.order_service.exception.UserNotFoundException;
import com.example.order_service.mapper.OrderMapper;
import com.example.order_service.model.OrderModel;
import com.example.order_service.repository.OrderRepository;
import com.example.order_service.service.KafkaConsumerService;
import com.example.order_service.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final KafkaConsumerService kafkaConsumerService;

    public OrderServiceImp(OrderRepository orderRepository,
                           OrderMapper orderMapper,
                           KafkaConsumerService kafkaConsumerService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.kafkaConsumerService = kafkaConsumerService;
    }

    private static final int MAX_WAIT_MS = 5000; // 5 secondes max
    private static final int CHECK_INTERVAL_MS = 100;

    @Override
    public OrderEvent createOrder(OrderEvent orderEvent) {

        int userId = orderEvent.getUserId();
        int productId = orderEvent.getProductId();
        int quantity = orderEvent.getQuantity();

        // Attente utilisateur
        long waited = 0;
        while (!kafkaConsumerService.isUserValid(userId) && waited < MAX_WAIT_MS) {
            try {
                Thread.sleep(CHECK_INTERVAL_MS);
                waited += CHECK_INTERVAL_MS;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread interrupted while waiting for Kafka user event", e);
            }
        }

        if (!kafkaConsumerService.isUserValid(userId)) {
            throw new UserNotFoundException(userId);
        }

        // Attente produit
        waited = 0;
        while (!kafkaConsumerService.isProductAvailable(productId, quantity) && waited < MAX_WAIT_MS) {
            try {
                Thread.sleep(CHECK_INTERVAL_MS);
                waited += CHECK_INTERVAL_MS;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread interrupted while waiting for Kafka product event", e);
            }
        }

        if (!kafkaConsumerService.isProductAvailable(productId, quantity)) {
            throw new ProductNotFoundException(productId);
        }

        OrderModel orderModel = orderMapper.toEntity(orderEvent);
        orderModel.setDate(LocalDateTime.now());

        OrderModel savedOrder = this.orderRepository.save(orderModel);
        return orderMapper.toDTO(savedOrder);
    }

    @Override
    public List<OrderEvent> getAllOrder() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderEvent updateOrder(int orderId, OrderEvent orderEvent) {
        Optional<OrderModel> existingOrder = orderRepository.findById(orderId);
        if (existingOrder.isEmpty()) {
            throw new OrderNotFoundException(orderId);
        }

        OrderModel orderToUpdate = existingOrder.get();
        orderToUpdate.setProductId(orderEvent.getProductId());
        orderToUpdate.setUserId(orderEvent.getUserId());
        orderToUpdate.setQuantity(orderEvent.getQuantity());
        orderToUpdate.setDate(LocalDateTime.now());

        OrderModel updated = orderRepository.save(orderToUpdate);
        return orderMapper.toDTO(updated);
    }

    @Override
    public Boolean delete(int orderId) {
        Optional<OrderModel> existingOrder = orderRepository.findById(orderId);
        if (existingOrder.isPresent()) {
            orderRepository.delete(existingOrder.get());
            return true;
        }
        throw new OrderNotFoundException(orderId);
    }
}
