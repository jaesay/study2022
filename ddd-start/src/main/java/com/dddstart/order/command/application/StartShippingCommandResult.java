package com.dddstart.order.command.application;

import com.dddstart.order.command.domain.OrderEntity;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class StartShippingCommandResult {
    private long orderId;
    // ...

    private StartShippingCommandResult() {}

    public static StartShippingCommandResult from(OrderEntity entity) {
        StartShippingCommandResult result = new StartShippingCommandResult();
        result.orderId = entity.getId();
        return result;
    }
}
