package com.dddstart.order.command.domain;

public interface ProductService {
    OrderProduct findOrderProduct(long productId, int quantity);
}
