package com.dddstart.order.infra.repository;

import com.dddstart.order.command.domain.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// TODO: domain에서 직접 의존하고 있음... => hexagonal architecture (port/adapter) 로 변경하면 조금 더 깔끔해질 것 같다..
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
