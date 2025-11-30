package org.eccommerce.inventoryservice.consumer;


import lombok.extern.slf4j.Slf4j;
import org.eccommerce.inventoryservice.dto.CheckedInventoryEvent;
import org.eccommerce.inventoryservice.dto.StartCheckInventoryEvent;
import org.eccommerce.inventoryservice.mapper.InventoryEventMapper;
import org.eccommerce.inventoryservice.service.InventoryCheckService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CheckInventoryEvent {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final InventoryCheckService inventoryCheckService;
    private final InventoryEventMapper inventoryEventMapper;
    
    public CheckInventoryEvent(KafkaTemplate<String, Object> kafkaTemplate, InventoryCheckService inventoryCheckService, InventoryEventMapper inventoryEventMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.inventoryCheckService = inventoryCheckService;
        this.inventoryEventMapper = inventoryEventMapper;
    }


    @KafkaListener(topics = {"${CHECK_INVENTORY_TOPIC}"})
    public void checkInventory(Object o) {
        if (o instanceof StartCheckInventoryEvent startCheckInventoryEvent) {
        log.info("The Check Inventory Event Received OrderNumber:{}",startCheckInventoryEvent.getOrderNumber());

        inventoryCheckService.controlInventoryStock(startCheckInventoryEvent);

        log.info("The Check Inventory Event Checked OrderNumber:{}",startCheckInventoryEvent.getOrderNumber());
            CheckedInventoryEvent checkedInventoryEvent =
                    inventoryEventMapper.StartEvenToCheckedEvent(startCheckInventoryEvent);
            kafkaTemplate.send("${CHECKED_INVENTORY_TOPIC},",checkedInventoryEvent);
        }
    }


}
