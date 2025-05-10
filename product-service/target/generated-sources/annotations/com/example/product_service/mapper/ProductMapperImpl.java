package com.example.product_service.mapper;

import com.example.product_service.event.ProductEvent;
import com.example.product_service.model.ProductModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T13:08:23+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductEvent toDTO(ProductModel productModel) {
        if ( productModel == null ) {
            return null;
        }

        ProductEvent productEvent = new ProductEvent();

        productEvent.setProductId( productModel.getProductId() );
        productEvent.setName( productModel.getName() );
        productEvent.setPrice( productModel.getPrice() );

        return productEvent;
    }

    @Override
    public ProductModel toEntity(ProductEvent dto) {
        if ( dto == null ) {
            return null;
        }

        ProductModel productModel = new ProductModel();

        productModel.setProductId( dto.getProductId() );
        productModel.setName( dto.getName() );
        productModel.setPrice( dto.getPrice() );

        return productModel;
    }
}
