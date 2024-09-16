package com.dragand.order.kafka;

import com.dragand.order.customer.CustomerResponse;
import com.dragand.order.order.model.PaymentMethod;
import com.dragand.order.product.dto.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalPrice,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
