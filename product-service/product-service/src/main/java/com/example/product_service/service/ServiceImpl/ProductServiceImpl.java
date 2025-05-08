package com.example.product_service.service.ServiceImpl;

import com.example.product_service.dto.ProductEvent;
import com.example.product_service.exception.ProductAlreadyExistException;
import com.example.product_service.exception.ProductNotFoundException;
import com.example.product_service.mapper.ProductMapper;
import com.example.product_service.model.ProductModel;
import com.example.product_service.repository.ProductRepository;
import com.example.product_service.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductEvent createProduct(ProductEvent productEvent) {
        ProductModel productModel = this.productRepository.findByName(productEvent.getName());
        if (productModel != null){
            throw new ProductAlreadyExistException(productEvent.getName());
        }
        ProductModel product = productMapper.toEntity(productEvent);
        return productMapper.toDTO(this.productRepository.save(product));
    }

    @Override
    public List<ProductEvent> getAllProduct() {
        List<ProductModel> get = this.productRepository.findAll();
        return get.stream().map(productMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ProductEvent getProductById(int productId) {
        ProductModel productModel = this.productRepository.findById(productId)
                .orElseThrow(()->new ProductNotFoundException(productId));

        return productMapper.toDTO(productModel);
    }

    @Override
    public ProductEvent updateProduct(int productId, ProductEvent productEvent) {
        ProductModel productModel = this.productRepository.findById(productId)
                .orElseThrow(()->new ProductNotFoundException(productId));
        productModel.setName(productEvent.getName());
        productModel.setPrice(productEvent.getPrice());
        productModel.setQunatity(productEvent.getQuantity());
        return productMapper.toDTO(this.productRepository.save(productModel));
    }

    @Override
    public Boolean deleteProduct(int productId) {
        Optional<ProductModel> exist = this.productRepository.findById(productId);
        if (exist.isPresent()){
            ProductModel pro = exist.get();
            this.productRepository.delete(pro);
            return true;
        }
        return false;
    }
}
