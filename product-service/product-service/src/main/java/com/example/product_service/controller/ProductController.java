package com.example.product_service.controller;

import com.example.product_service.event.ProductEvent;
import com.example.product_service.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "interactive product documentation", description = "APIs RESTful documentation")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/save")
    @Operation(summary = "add product", description = "save product in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = " saved product successful"),
            @ApiResponse(responseCode = "404", description = "product not found"),
            @ApiResponse(responseCode = "500", description = " error server")
    })
    public ResponseEntity<ProductEvent> create(@PathVariable ProductEvent productEvent){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productService.createProduct(productEvent));
    }

    @GetMapping()
    @Operation(summary = "get all products", description = "get all products in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = " get product successful"),
            @ApiResponse(responseCode = "404", description = "product not found"),
            @ApiResponse(responseCode = "500", description = " error server")
    })
    public ResponseEntity<List<ProductEvent>> get(){
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.getAllProduct());
    }

    @GetMapping("/{productId}")
    @Operation(summary = "get product", description = "get product in database with her id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = " get product successful"),
            @ApiResponse(responseCode = "404", description = "product not found"),
            @ApiResponse(responseCode = "500", description = " error server")
    })
    public ResponseEntity<ProductEvent> getById(@PathVariable int productId){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.productService.getProductById(productId));
    }

    @PutMapping("/update/{productId}")
    @Operation(summary = "update product", description = "update product in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = " updated product successful"),
            @ApiResponse(responseCode = "404", description = "product not found"),
            @ApiResponse(responseCode = "500", description = " error server")
    })
    public ResponseEntity<ProductEvent> update(@PathVariable int productId, @RequestBody ProductEvent productEvent){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.productService.updateProduct(productId, productEvent));
    }

    @DeleteMapping("/delete/{productId}")
    @Operation(summary = "delete user", description = "delete product in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = " deleted product successful"),
            @ApiResponse(responseCode = "404", description = "product not found"),
            @ApiResponse(responseCode = "500", description = " error server")
    })
    public ResponseEntity<Boolean> delete(@PathVariable int productId){
        boolean isDeleted = this.productService.deleteProduct(productId);
        if (isDeleted){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }
}
