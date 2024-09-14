package com.dragand.order.orderline.repository;

import com.dragand.order.orderline.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
}
