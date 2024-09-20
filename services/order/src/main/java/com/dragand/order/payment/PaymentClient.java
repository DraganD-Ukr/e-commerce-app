package com.dragand.order.payment;

import com.dragand.order.payment.dto.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-service", url = "${application.config.payment-url}")
public interface PaymentClient {

    @PostMapping
    Integer requestPayment(@RequestBody PaymentRequest paymentResponse);

}
