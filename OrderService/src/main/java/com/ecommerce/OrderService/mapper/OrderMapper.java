package com.ecommerce.OrderService.mapper;


import com.ecommerce.OrderService.dto.OrderResponse;
import com.ecommerce.OrderService.dto.OrderSaveRequest;
import com.ecommerce.OrderService.dto.PlacedOrderEvent;
import com.ecommerce.OrderService.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    Order saveRequesToOrder(OrderSaveRequest orderSaveRequest);

    OrderResponse orderToResponse(Order order);

    PlacedOrderEvent orderToEvent(Order order);
}
