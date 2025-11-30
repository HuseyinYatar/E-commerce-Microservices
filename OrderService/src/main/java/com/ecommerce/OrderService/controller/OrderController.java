package com.ecommerce.OrderService.controller;


import com.ecommerce.OrderService.dto.OrderResponse;
import com.ecommerce.OrderService.dto.OrderSaveRequest;
import com.ecommerce.OrderService.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("${order.request.mapping}")
@RestController
public class OrderController {

    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("${order.place}")
    public OrderResponse placeOrder(@RequestBody OrderSaveRequest orderSaveRequest) {
        return orderService.placeOrder(orderSaveRequest);

    }

    @GetMapping("${order.list}")
    public List<OrderResponse> listOrder() {
        return orderService.listOrder();
    }


}
