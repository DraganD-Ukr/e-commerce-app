package com.dragand.ecommerce.product.dto;

import jakarta.validation.constraints.NotBlank;

public record ProductPurchaseRequest(
        @NotBlank(message = "ProductID is mandatory ")
        Integer productId,

        @NotBlank(message = "Quantity is mandatory ")
        Double quantity
) {
}
