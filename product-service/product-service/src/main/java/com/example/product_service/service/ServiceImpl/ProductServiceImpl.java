package com.example.product_service.service.ServiceImpl;

import com.example.product_service.event.ProductEvent;
import com.example.product_service.exception.ProductAlreadyExistException;
import com.example.product_service.exception.ProductNotFoundException;
import com.example.product_service.mapper.ProductMapper;
import com.example.product_service.model.ProductModel;
import com.example.product_service.repository.ProductRepository;
import com.example.product_service.service.KafkaProducerService;
import com.example.product_service.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final KafkaProducerService kafkaProducerService;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, KafkaProducerService kafkaProducerService) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.kafkaProducerService = kafkaProducerService;
    }

    @Override
    public ProductEvent createProduct(ProductEvent productEvent) {
        ProductModel productModel = this.productRepository.findByName(productEvent.getName());
        if (productModel != null){
            throw new ProductAlreadyExistException(productEvent.getName());
        }
        ProductModel product = productMapper.toEntity(productEvent);
        ProductModel saved = this.productRepository.save(product);
        ProductEvent savedEvent = productMapper.toDTO(saved);
        kafkaProducerService.SendProductEvent(savedEvent);
        return savedEvent;
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

         productMapper.toDTO(this.productRepository.save(productModel));
         kafkaProducerService.SendProductEvent(productEvent);
         return productEvent;
    }

    @Override
    public Boolean deleteProduct(int productId) {
        Optional<ProductModel> exist = this.productRepository.findById(productId);
        if (exist.isPresent()){
            ProductModel pro = exist.get();
            this.productRepository.delete(pro);

            ProductEvent productEvent = productMapper.toDTO(pro);
            kafkaProducerService.SendProductEvent(productEvent);
            return true;
        }
        return false;
    }
}
