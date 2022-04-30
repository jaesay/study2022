package com.ddd.order.command.application;

import com.ddd.order.command.domain.OrderEntity;
import com.ddd.order.infra.repository.OrderRepository;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StartShippingService {
    private final OrderRepository orderRepository;

    public StartShippingService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public StartShippingCommandResult startShipping(StartShippingCommand command) {
        OrderEntity orderEntity = orderRepository.findById(command.getOrderId()).orElseThrow(() -> new RuntimeException("order not found"));
        // version 정보를 넘기지 않으면 배송상태 변경 폼에서 보여준 주문(배송지정보)과 배송상태 완료된 주문(배송지정보)가 다를 수 있음
        // 배송상태 변경 폼에 있는 동안 누군가가 애그리거트를 수정함
        if (!orderEntity.matchVersion(command.getVersion())) {
            throw new OptimisticLockingFailureException("tx version conflicted");
        }
        // 사용자의 배송지 변경 api와 거의 동시에 일어났지만 조금 늦게 commit 되면 OptimisticLockingException 발생
        orderEntity.startShipping();
        return StartShippingCommandResult.from(orderEntity);
    }
}
