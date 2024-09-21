package com.dragand.order.customer.dto;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
