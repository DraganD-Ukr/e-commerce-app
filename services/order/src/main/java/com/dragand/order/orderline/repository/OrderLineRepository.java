package com.dragand.order.orderline.repository;

import com.dragand.order.orderline.dto.OrderLineResponse;
import com.dragand.order.orderline.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
    List<OrderLine> findAllByOrderId(Integer orderId);
}
