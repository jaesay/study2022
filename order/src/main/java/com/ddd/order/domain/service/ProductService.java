package com.ddd.order.domain.service;

import com.ddd.order.domain.model.OrderProduct;

public interface ProductService {
    OrderProduct findOrderProduct(long productId, int quantity);
}
