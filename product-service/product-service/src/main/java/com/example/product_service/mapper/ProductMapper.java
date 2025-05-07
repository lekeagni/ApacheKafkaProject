package com.example.product_service.mapper;

import com.example.product_service.event.ProductEvent;
import com.example.product_service.model.ProductModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductEvent toDTO(ProductModel productModel);
    ProductModel toEntity(ProductEvent productEvent);
}
