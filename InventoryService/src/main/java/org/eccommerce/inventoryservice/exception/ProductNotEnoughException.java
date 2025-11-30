package org.eccommerce.inventoryservice.exception;

public class ProductNotEnoughException extends RuntimeException {
    public ProductNotEnoughException(String s) {
        super(s);
    }
}
