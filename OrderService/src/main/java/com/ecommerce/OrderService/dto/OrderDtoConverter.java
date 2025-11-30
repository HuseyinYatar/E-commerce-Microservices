package com.ecommerce.OrderService.dto;

import com.ecommerce.OrderService.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderDtoConverter {

    public OrderResponse orderToOrderResponse(Order order)
    {
        return OrderResponse.builder().
                orderNumber(order.getOrderNumber())
                .creationTime(order.getCreationTime()).
                build();
    }

    public Order requestToOrder(OrderSaveRequest orderSaveRequest)
    {
        return Order.builder().
                creationTime(orderSaveRequest.getCreationTime())
                .orderNumber(orderSaveRequest.getOrderNumber()).
                build();
    }
}
