package com.dragand.order.product.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
    @NotNull(message = "Product is mandatory")
    Integer productId,
    @Positive(message = "Quantity is mandatory and need to be positive")
    Double quantity
) {
}
