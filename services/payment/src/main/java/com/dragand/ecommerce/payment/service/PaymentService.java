package com.dragand.ecommerce.payment.service;

import com.dragand.ecommerce.notification.NotificationProducer;
import com.dragand.ecommerce.notification.PaymentNotificationRequest;
import com.dragand.ecommerce.payment.dto.PaymentMapper;
import com.dragand.ecommerce.payment.dto.PaymentRequest;
import com.dragand.ecommerce.payment.repostiory.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;

    public Integer createPayment(PaymentRequest request) {
        var payment = paymentRepository.save(mapper.toPayment(request));

        notificationProducer.sendNotification(new PaymentNotificationRequest(
                request.orderReference(),
                request.amount(),
                request.paymentMethod(),
                request.customer().firstName(),
                request.customer().lastName(),
                request.customer().email()
        ));
        return payment.getId();
    }


}
