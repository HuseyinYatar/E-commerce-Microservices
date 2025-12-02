package com.ecommerce.OrderService.dto;

import com.ecommerce.OrderService.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private LocalDateTime creationTime=LocalDateTime.now();
    private Long customerId;
    private List<OrderItem> orderItems;
    private String orderNumber;
}
