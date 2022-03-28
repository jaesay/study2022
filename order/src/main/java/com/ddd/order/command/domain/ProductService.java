package com.ddd.order.command.domain;

import com.ddd.order.command.domain.OrderProduct;

public interface ProductService {
    OrderProduct findOrderProduct(long productId, int quantity);
}
