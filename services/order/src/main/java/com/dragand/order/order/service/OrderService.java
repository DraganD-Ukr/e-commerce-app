package com.dragand.order.order.service;

import com.dragand.order.customer.CustomerClient;
import com.dragand.order.customer.ProductClient;
import com.dragand.order.exception.CustomerNotFoundException;
import com.dragand.order.order.dto.OrderMapper;
import com.dragand.order.order.dto.OrderRequest;
import com.dragand.order.order.repository.OrderRepository;
import com.dragand.order.orderline.dto.OrderLineRequest;
import com.dragand.order.orderline.service.OrderLineService;
import com.dragand.order.product.dto.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;

    public Integer createOrder(OrderRequest request) {
//        Check the customer --> OpenFeign
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new CustomerNotFoundException("Cannot create order: No customer exists with id:" + request.customerId()));

//        Purchase the product --> product-service
        this.productClient.purchaseProducts(request.products());

//        Persist order
        var order = this.orderRepository.save(mapper.toOrder(request));

//        Persist order lines
        for (PurchaseRequest purchaseRequest: request.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

//      todo  Start payment process


//        Send the order confirmation --> notification-service

        return null;
    }

}
