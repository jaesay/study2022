package com.ddd.order.infra.repository;

import com.ddd.order.domain.aggregate.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
