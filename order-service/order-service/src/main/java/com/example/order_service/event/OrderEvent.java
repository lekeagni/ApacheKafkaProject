package com.example.order_service.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {

    private int orderId;
    private int userId;
    private int productId;
    private LocalDateTime date;
    private int quantity;
}
