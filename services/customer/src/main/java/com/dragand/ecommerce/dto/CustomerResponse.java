package com.dragand.ecommerce.dto;

import com.dragand.ecommerce.model.Address;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {
}
