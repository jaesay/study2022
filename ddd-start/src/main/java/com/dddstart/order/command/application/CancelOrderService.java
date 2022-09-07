package com.dddstart.order.command.application;

import com.ddd.order.command.domain.*;
import com.dddstart.order.command.domain.*;
import com.dddstart.order.infra.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class CancelOrderService {

    private final OrderRepository repository;
    private final RefundService refundService;
    private final MemberService memberService;
    private final CancelPolicy cancelPolicy;

    public CancelOrderService(OrderRepository repository, RefundService refundService, MemberService memberService, CancelPolicy cancelPolicy) {
        this.repository = repository;
        this.refundService = refundService;
        this.memberService = memberService;
        this.cancelPolicy = cancelPolicy;
    }

    public CancelOrderCommandResult cancel(CancelOrderCommand command) {
        OrderEntity orderEntity = repository.findById(command.getOrderId()).orElseThrow(() -> new RuntimeException("order not found"));
        Canceller canceller = memberService.findCanceller(orderEntity.getOrderer().getMemberId());
        if (!cancelPolicy.hasCancellationPermission(orderEntity, canceller)) {
            throw new RuntimeException("No Cancellable Permission");
        }
        orderEntity.cancel();
        refundService.refund(orderEntity.getId());

        return CancelOrderCommandResult.from(orderEntity);
    }
}
