package com.example.order_service.controller;

import com.example.order_service.event.OrderEvent;
import com.example.order_service.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@Tag(name = "order table test", description = "APIs REST test with Swagger")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/save")
    @Operation(summary = "add order", description = "save order in the database afer ckeck product and user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "saved order successful"),
            @ApiResponse(responseCode = "400", description = "failed to add order. invalid user or product unavailable"),
            @ApiResponse(responseCode = "500", description = "server error")

    })
    public ResponseEntity<OrderEvent> create( @RequestBody OrderEvent orderEvent){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.orderService.createOrder( orderEvent));
    }
}
