package com.ddd.order.command.domain;

public interface DiscountCalculationService {
    Money calculateDiscountAmounts(OrderEntity orderEntity);
}
