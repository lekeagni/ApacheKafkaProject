package com.example.order_service.mapper;

import com.example.order_service.event.OrderEvent;
import com.example.order_service.model.OrderModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderEvent toDTO(OrderModel orderModel);
    OrderModel toEntity(OrderEvent orderEvent);

}
