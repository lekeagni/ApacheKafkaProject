package com.example.product_service.service;

import com.example.product_service.dto.ProductEvent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    public ProductEvent createProduct(ProductEvent productEvent);

    public List<ProductEvent> getAllProduct();

    public ProductEvent getProductById(int productId);

    public ProductEvent updateProduct(int productId, ProductEvent productEvent);

    public Boolean deleteProduct(int productId);
}
