package com.example.product_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @Column(name = "NAME",nullable = false,length = 50)
    private String name;

    @Column(name = "PRICE",nullable = false)
    private Double price;

    @Column(name = "QUANTITY",nullable = false)
    private int qunatity;
}
