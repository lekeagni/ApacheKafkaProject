package com.example.product_service.service;

import com.example.product_service.event.ProductEvent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    public ProductEvent createProduct(ProductEvent productEvent);

    public List<ProductEvent> getAllProduct();

    public ProductEvent updateProduct(int productId, ProductEvent productEvent);

    public Boolean deleteProduct(int productId);
}
