package com.dddstart.order.infra.domain.service;

import com.dddstart.order.command.domain.CancelPolicy;
import com.dddstart.order.command.domain.Canceller;
import com.dddstart.order.command.domain.OrderEntity;
import com.dddstart.order.common.DomainService;

@DomainService // 한 애그리거트에 넣기 애매한 도메인 기능을 도메인 서비스로 구현
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
