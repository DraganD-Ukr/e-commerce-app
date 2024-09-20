package com.dragand.ecommerce.payment.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        String id,
        @NotBlank(message = "First name is required")
        String firstName,
        @NotBlank(message = "Last name is required")
        String lastName,
        @NotBlank(message = "Email is required")
        @Email(message = "Email is not in a correct format")
        String email
) {
}
