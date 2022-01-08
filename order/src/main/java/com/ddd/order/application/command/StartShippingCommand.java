package com.ddd.order.application.command;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Min;

@Getter @ToString @Builder
public class StartShippingCommand {
    @Min(1)
    private long orderId;
    @Min(0)
    private long version;
}
