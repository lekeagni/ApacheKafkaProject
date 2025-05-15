package com.example.order_service.service;

import com.example.order_service.model.ProductModel;
import com.example.order_service.model.UserModel;
import com.example.order_service.event.ProductEvent;
import com.example.order_service.event.UserEvent;
import com.example.order_service.repository.ProductRepository;
import com.example.order_service.repository.UserRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KafkaConsumerService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public KafkaConsumerService(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @KafkaListener(
            topics = "user-events",
            groupId = "order-group",
            containerFactory = "userKafkaListenerContainerFactory"
    )
    public void consumeUser(UserEvent userEvent) {
        System.out.println("Reçu depuis 'user-events': " + userEvent);

        UserModel user = new UserModel();
        user.setUserId(userEvent.getUserId());
        user.setName(userEvent.getName());
        user.setEmail(userEvent.getEmail());
        user.setPhone(userEvent.getPhone());
        userRepository.save(user);
    }

    @KafkaListener(
            topics = "product-events",
            groupId = "order-group",
            containerFactory = "productKafkaListenerContainerFactory"
    )
    public void consumeProduct(ProductEvent productEvent) {
        System.out.println("Reçu depuis 'product-events': " + productEvent);

        ProductModel product = new ProductModel();
        product.setProductId(productEvent.getProductId());
        product.setName(productEvent.getName());
        product.setQuantity(productEvent.getQuantity());
        product.setPrice(productEvent.getPrice());
        productRepository.save(product);
    }

    public boolean isUserValid(int userId) {
        System.out.println("Vérification utilisateur avec ID: " + userId);
        return userRepository.existsById(userId);
    }

    public boolean isProductAvailable(int productId, int requestedQuantity) {
        Optional<ProductModel> product = productRepository.findById(productId);
        return product.isPresent() && product.get().getQuantity() >= requestedQuantity;
    }
}
