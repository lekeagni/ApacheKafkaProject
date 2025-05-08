package com.example.product_service.exception;

public class ProductAlreadyExistException extends CustomException {
    public ProductAlreadyExistException(String name) {

        super("product already exist with"+name);
    }
}
