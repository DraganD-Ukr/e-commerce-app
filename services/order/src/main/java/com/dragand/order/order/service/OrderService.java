package com.dragand.order.order.service;

import com.dragand.order.customer.CustomerClient;
import com.dragand.order.product.ProductClient;
import com.dragand.order.exception.CustomerNotFoundException;
import com.dragand.order.kafka.OrderConfirmation;
import com.dragand.order.kafka.OrderProducer;
import com.dragand.order.order.dto.OrderMapper;
import com.dragand.order.order.dto.OrderRequest;
import com.dragand.order.order.dto.OrderResponse;
import com.dragand.order.order.repository.OrderRepository;
import com.dragand.order.orderline.dto.OrderLineRequest;
import com.dragand.order.orderline.service.OrderLineService;
import com.dragand.order.payment.PaymentClient;
import com.dragand.order.payment.dto.PaymentRequest;
import com.dragand.order.product.dto.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder(OrderRequest request) {

//        Check the customer --> OpenFeign(customer service)
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new CustomerNotFoundException("Cannot create order: No customer exists with id:" + request.customerId()));

//        Purchase the product --> OpenFeign(product-service)
        var purchasedProducts = this.productClient.purchaseProducts(request.products());

//        Save order
        var order = this.orderRepository.save(mapper.toOrder(request));

//        Persist order lines
        for (PurchaseRequest purchaseRequest : request.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

//       Start payment process
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestPayment(paymentRequest);


//        Send the order confirmation --> notification-service
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                ));
        return order.getId();
    }


    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return orderRepository.findById(Long.valueOf(orderId))
                .map(mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", orderId)));
    }
}
