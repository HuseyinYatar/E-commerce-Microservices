package org.eccommerce.inventoryservice.service;


import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.eccommerce.inventoryservice.dto.StartCheckInventoryEvent;
import org.eccommerce.inventoryservice.exception.ProductNotEnoughException;
import org.eccommerce.inventoryservice.repository.InventoryRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InventoryCheckService {

    private final InventoryRepository inventoryRepository;

    public InventoryCheckService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional
    public void controlInventoryStock(StartCheckInventoryEvent inventoryControlStockEvent) {
        inventoryControlStockEvent.getOrderItems().forEach
                ((item) ->
                {
                    inventoryRepository.findByQuantityGreaterThanEqual(item.getQuantity()).orElseThrow
                            (() -> {
                                log.error("Insufficient stock for product id: {} Requested: {}",
                                 item.getProductID(), item.getQuantity());
                                return new ProductNotEnoughException
                                        ("Insufficient stock for product id: "+item.getProductID().toString());
                                    }
                            );
                });

    }


}




