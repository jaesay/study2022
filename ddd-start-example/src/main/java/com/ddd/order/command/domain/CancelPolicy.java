package com.ddd.order.command.domain;

public interface CancelPolicy {
    boolean hasCancellationPermission(OrderEntity order, Canceller canceller);
}
