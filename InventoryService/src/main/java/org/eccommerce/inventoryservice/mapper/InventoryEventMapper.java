package org.eccommerce.inventoryservice.mapper;


import org.eccommerce.inventoryservice.dto.CheckedInventoryEvent;
import org.eccommerce.inventoryservice.dto.StartCheckInventoryEvent;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InventoryEventMapper {

    CheckedInventoryEvent StartEvenToCheckedEvent(StartCheckInventoryEvent startCheckInventoryEvent);
}
