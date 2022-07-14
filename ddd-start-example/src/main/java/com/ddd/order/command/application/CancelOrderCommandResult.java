package com.ddd.order.command.application;

import com.ddd.order.command.domain.OrderEntity;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class CancelOrderCommandResult {
    private long orderId;
    // ...

    private CancelOrderCommandResult() {}

    public static CancelOrderCommandResult from(OrderEntity entity) {
        CancelOrderCommandResult result = new CancelOrderCommandResult();
        result.orderId = entity.getId();
        return result;
    }
}
