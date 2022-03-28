package com.ddd.order.infra.domain.service;

import com.ddd.order.command.domain.CancelPolicy;
import com.ddd.order.command.domain.Canceller;
import com.ddd.order.command.domain.OrderEntity;
import com.ddd.order.common.DomainService;

@DomainService
public class SecurityCancelPolicy implements CancelPolicy {

    @Override
    public boolean hasCancellationPermission(OrderEntity order, Canceller canceller) {
        return isCancellerOrderer(order, canceller) || isCurrentUserAdminRole();
    }

    private boolean isCancellerOrderer(OrderEntity order, Canceller canceller) {
        return order.getOrderer().getMemberId() == canceller.getMemberId();
    }

    private boolean isCurrentUserAdminRole() {
        return false;
    }
}
