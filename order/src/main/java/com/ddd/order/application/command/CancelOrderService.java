package com.ddd.order.application.command;

import com.ddd.order.domain.model.OrderEntity;
import com.ddd.order.domain.service.RefundService;
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
