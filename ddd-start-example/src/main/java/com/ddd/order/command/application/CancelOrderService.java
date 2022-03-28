package com.ddd.order.command.application;

import com.ddd.order.command.domain.OrderEntity;
import com.ddd.order.command.domain.RefundService;
import com.ddd.order.infra.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class CancelOrderService {

    private final OrderRepository repository;
    private final RefundService refundService;

    public CancelOrderService(OrderRepository repository, RefundService refundService) {
        this.repository = repository;
        this.refundService = refundService;
    }

    public CancelOrderCommandResult cancel(CancelOrderCommand command) {
        OrderEntity orderEntity = repository.findById(command.getOrderId()).orElseThrow(() -> new RuntimeException("order not found"));
        orderEntity.cancel();
        refundService.refund(orderEntity.getId());

        return CancelOrderCommandResult.from(orderEntity);
    }
}
