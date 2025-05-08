package com.example.product_service.mapper;

import com.example.product_service.dto.ProductDTO;
import com.example.product_service.model.ProductModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-08T11:14:04+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDTO toDTO(ProductModel productModel) {
        if ( productModel == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setProductId( productModel.getProductId() );
        productDTO.setName( productModel.getName() );
        productDTO.setPrice( productModel.getPrice() );

        return productDTO;
    }

    @Override
    public ProductModel toEntity(ProductDTO dto) {
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
