package com.example.order_service.repository;

import com.example.order_service.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel,Integer> {
}
