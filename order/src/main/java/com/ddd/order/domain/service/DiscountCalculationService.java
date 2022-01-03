package com.ddd.order.domain.service;

import com.ddd.order.domain.model.Money;
import com.ddd.order.domain.model.OrderEntity;

public interface DiscountCalculationService {
    Money calculateDiscountAmounts(OrderEntity orderEntity);
}
