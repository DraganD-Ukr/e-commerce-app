package com.dragand.order.payment.dto;

import com.dragand.order.customer.dto.CustomerResponse;
import com.dragand.order.order.model.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(

        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer

) {
}
