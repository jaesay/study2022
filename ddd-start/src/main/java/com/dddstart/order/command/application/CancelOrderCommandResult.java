package com.dddstart.order.command.application;

import com.dddstart.order.command.domain.OrderEntity;
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
