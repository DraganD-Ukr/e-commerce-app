package com.dragand.ecommerce.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(

        Integer id,

        @NotBlank(message = "Product name is required")
        String name,

        @NotBlank(message = "Product description is required")
        String description,

        @NotBlank
        @Positive(message = "Available quantity needs to be positive")
        Double availableQuantity,

        @NotBlank
        @Positive(message = "Price needs to be positive")
        BigDecimal price,

        @NotBlank(message = "Available quantity needs to be positive")
        Integer categoryId

) {



}
