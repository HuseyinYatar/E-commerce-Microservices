package org.eccommerce.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StartCheckInventoryEvent {
    private LocalDateTime creationTime;
    private Long CustomerID;
    private List<OrderItem> orderItems;
    private String orderNumber;
}
