package com.example.order_service.service.ServiceImpl;

import com.example.order_service.event.OrderEvent;
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
import java.util.stream.Collectors;

@Service
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private  final KafkaConsumerService kafkaConsumerService;

    public OrderServiceImp(OrderRepository orderRepository, OrderMapper orderMapper, KafkaConsumerService kafkaConsumerService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.kafkaConsumerService = kafkaConsumerService;
    }

    @Override
    public OrderEvent createOrder( OrderEvent orderEvent) {

        int userId = orderEvent.getUserId();
        int productId = orderEvent.getProductId();
        int quantity = orderEvent.getQuantity();

        if(!kafkaConsumerService.isUserValid(userId)){
            throw  new UserNotFoundException(userId);
        }

        if (!kafkaConsumerService.isProductAvailable(productId, quantity)){
            throw new ProductNotFoundException(productId);
        }
        OrderModel orderModel = orderMapper.toEntity(orderEvent);

        orderModel.setDate(LocalDateTime.now());

        return orderMapper.toDTO(this.orderRepository.save(orderModel));
    }

    @Override
    public List<OrderEvent> getAllOrder() {
        List<OrderModel> get = this.orderRepository.findAll();
        return get.stream().map(orderMapper::toDTO)
                .collect(Collectors.toList());
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
