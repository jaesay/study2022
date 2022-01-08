package com.ddd.order.application.command;

import com.ddd.order.domain.model.OrderEntity;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class StartShippingCommandResult {
    private long orderId;
    // ...

    /* Constructor */
    private StartShippingCommandResult() {}

    /* Static Factory Method */
    public static StartShippingCommandResult from(OrderEntity entity) {
        StartShippingCommandResult result = new StartShippingCommandResult();
        result.orderId = entity.getId();
        return result;
    }
}
