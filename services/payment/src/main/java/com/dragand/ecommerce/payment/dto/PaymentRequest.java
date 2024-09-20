package com.dragand.ecommerce.payment.dto;

import com.dragand.ecommerce.payment.model.Customer;
import com.dragand.ecommerce.payment.model.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        Customer customer
) {


}
