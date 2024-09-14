package com.dragand.order.orderline.service;

import com.dragand.order.orderline.dto.OrderLineMapper;
import com.dragand.order.orderline.dto.OrderLineRequest;
import com.dragand.order.orderline.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper mapper;

    public Integer saveOrderLine(OrderLineRequest request) {
        var order = mapper.toOrderLine(request);
        return orderLineRepository.save(order).getId();
    }

}
