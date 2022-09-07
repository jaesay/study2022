package com.dddstart.order.command.domain;

public interface CancelPolicy {
    boolean hasCancellationPermission(OrderEntity order, Canceller canceller);
}
