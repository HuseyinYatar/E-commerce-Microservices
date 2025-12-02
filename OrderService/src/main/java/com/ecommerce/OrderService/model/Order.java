package com.ecommerce.OrderService.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;

    @ElementCollection
    @CollectionTable(name="order_items",joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderItem> orderItems;

    @CreationTimestamp
    private LocalDateTime creationTime;

    private String orderNumber;
}
