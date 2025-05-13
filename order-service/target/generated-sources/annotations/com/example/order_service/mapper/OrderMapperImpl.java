package com.example.order_service.mapper;

import com.example.order_service.event.OrderEvent;
import com.example.order_service.model.OrderModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-12T16:32:25+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderEvent toDTO(OrderModel orderModel) {
        if ( orderModel == null ) {
            return null;
        }

        OrderEvent orderEvent = new OrderEvent();

        orderEvent.setOrderId( orderModel.getOrderId() );
        orderEvent.setUserId( orderModel.getUserId() );
        orderEvent.setProductId( orderModel.getProductId() );
        orderEvent.setDate( orderModel.getDate() );
        orderEvent.setQuantity( orderModel.getQuantity() );

        return orderEvent;
    }

    @Override
    public OrderModel toEntity(OrderEvent orderEvent) {
        if ( orderEvent == null ) {
            return null;
        }

        OrderModel orderModel = new OrderModel();

        orderModel.setOrderId( orderEvent.getOrderId() );
        orderModel.setUserId( orderEvent.getUserId() );
        orderModel.setProductId( orderEvent.getProductId() );
        orderModel.setDate( orderEvent.getDate() );
        orderModel.setQuantity( orderEvent.getQuantity() );

        return orderModel;
    }
}
