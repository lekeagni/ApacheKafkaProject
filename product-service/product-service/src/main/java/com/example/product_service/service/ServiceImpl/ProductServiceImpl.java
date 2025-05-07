package com.example.product_service.service.ServiceImpl;

import com.example.product_service.event.ProductEvent;
import com.example.product_service.repository.ProductRepository;
import com.example.product_service.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductEvent createProduct(ProductEvent productEvent) {
        return null;
    }

    @Override
    public List<ProductEvent> getAllProduct() {
        return List.of();
    }

    @Override
    public ProductEvent updateProduct(int productId, ProductEvent productEvent) {
        return null;
    }

    @Override
    public Boolean deleteProduct(int productId) {
        return null;
    }
}
