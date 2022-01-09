package com.ddd.order.application.command;

import com.ddd.order.domain.model.OrderEntity;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class CancelOrderCommandResult {
    private long orderId;
    // ...

    /* Constructor */
    private CancelOrderCommandResult() {}

    /* Static Factory Method */
    public static CancelOrderCommandResult from(OrderEntity entity) {
        CancelOrderCommandResult result = new CancelOrderCommandResult();
        result.orderId = entity.getId();
        return result;
    }
}
