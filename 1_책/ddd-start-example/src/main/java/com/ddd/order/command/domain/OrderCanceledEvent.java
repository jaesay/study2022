package com.ddd.order.command.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderCanceledEvent {
    // 이벤트는 핸들러에서 이벤트를 처리하는데 필요한 데이터를 포함한다.
    private long orderId;
    private LocalDateTime createdAt;

    private OrderCanceledEvent() {}

    public static OrderCanceledEvent create(long orderId) {
        OrderCanceledEvent event = new OrderCanceledEvent();
        event.orderId = orderId;
        event.createdAt = LocalDateTime.now();
        return event;
    }
}
