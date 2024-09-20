package com.dragand.ecommerce.payment.controller;

import com.dragand.ecommerce.payment.dto.PaymentRequest;
import com.dragand.ecommerce.payment.model.Payment;
import com.dragand.ecommerce.payment.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payment")
public class PaymentController {

    private final PaymentService paymentService;


    @PostMapping
    public ResponseEntity<Integer> createPayment(@RequestBody @Valid PaymentRequest request) {
        return ResponseEntity.ok(paymentService.createPayment(request));
    }

}
