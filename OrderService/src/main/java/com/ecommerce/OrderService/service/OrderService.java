package com.ecommerce.OrderService.service;


import com.ecommerce.OrderService.dto.OrderDtoConverter;
import com.ecommerce.OrderService.dto.OrderResponse;
import com.ecommerce.OrderService.dto.OrderSaveRequest;
import com.ecommerce.OrderService.dto.PlacedOrderEvent;
import com.ecommerce.OrderService.model.Order;
import com.ecommerce.OrderService.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final OrderDtoConverter orderDtoConverter;
    public OrderService(OrderRepository orderRepository, KafkaTemplate<String, Object> kafkaTemplate, OrderDtoConverter orderDtoConverter) {
        this.orderRepository = orderRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.orderDtoConverter = orderDtoConverter;
    }

    @Value("${PLACE_ORDER_TOPIC}")
    private String place_order;


    public OrderResponse placeOrder(OrderSaveRequest orderSaveRequest) {
        Order order = orderDtoConverter.requestToOrder(orderSaveRequest);
        orderRepository.save(order);
        log.info("Order placed OrderID:{},Order CreationTime:{},OrderNumber {} "
                , order.getId(), order.getCreationTime(), order.getOrderNumber());
        OrderResponse orderResponse = orderDtoConverter.orderToOrderResponse(order);

        PlacedOrderEvent event = PlacedOrderEvent.builder()
                .orderNumber(order.getOrderNumber())
                .orderItems(orderResponse.getOrderItems())
                .creationTime(orderResponse.getCreationTime())
                .CustomerID(orderResponse.getCustomerID())
                .build();

        kafkaTemplate.send(place_order, event);
        return orderResponse;
    }

    public List<OrderResponse> listOrder() {
        return orderRepository.findAll().stream().map(orderDtoConverter::orderToOrderResponse).toList();
    }


}
