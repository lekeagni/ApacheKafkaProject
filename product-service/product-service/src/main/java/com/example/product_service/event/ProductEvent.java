package com.example.product_service.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEvent {
    private int productId;
    private String name;
    private Double price;
    private int quantity;
}
