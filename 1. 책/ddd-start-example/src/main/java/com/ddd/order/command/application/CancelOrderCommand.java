package com.ddd.order.command.application;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;

@Getter @Setter @ToString
public class CancelOrderCommand {
    @Min(1)
    private long orderId;

    public CancelOrderCommand(long orderId) {
        this.orderId = orderId;
    }
}
