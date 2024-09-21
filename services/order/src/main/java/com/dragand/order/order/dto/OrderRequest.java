package com.dragand.order.order.dto;

import com.dragand.order.order.model.PaymentMethod;
import com.dragand.order.product.dto.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "Order amount has to be positive")
        BigDecimal amount,
        @NotNull(message = "Payment method has to be specified")
        PaymentMethod paymentMethod,
        @NotBlank(message = "Customer has to be present")
        String customerId,
        @NotEmpty(message = "At least one product has to be purchased")
        List<PurchaseRequest> products
) {
}
