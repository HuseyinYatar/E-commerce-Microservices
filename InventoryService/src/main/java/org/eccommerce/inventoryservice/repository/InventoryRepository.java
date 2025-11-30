package org.eccommerce.inventoryservice.repository;

import org.eccommerce.inventoryservice.model.InventoryItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InventoryRepository extends JpaRepository<InventoryItems, UUID> {
    Optional<InventoryItems> findByQuantityGreaterThanEqual(Long quantityIsGreaterThan);
}
