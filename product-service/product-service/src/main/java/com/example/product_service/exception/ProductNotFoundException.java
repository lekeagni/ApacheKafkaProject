package com.example.product_service.exception;

public class ProductNotFoundException extends CustomException {
    public ProductNotFoundException(Integer productId) {

        super("Product no found"+productId);
    }
}
