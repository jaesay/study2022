package com.ddd.order.command.application;

import com.ddd.order.command.domain.OrderEntity;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class PlaceOrderCommandResult {
    private long orderId;

    private PlaceOrderCommandResult() {}

    public static PlaceOrderCommandResult from(OrderEntity orderEntity) {
        PlaceOrderCommandResult result = new PlaceOrderCommandResult();
        result.orderId = orderEntity.getId();
        return result;
    }
}
