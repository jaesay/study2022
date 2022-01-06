package com.ddd.order.application.command;

import com.ddd.order.domain.model.*;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter @ToString
public class PlaceOrderCommandResult {
    private long orderId;
    private Orderer orderer;
    private List<OrderProduct> orderProducts;
    private ShippingInfo shippingInfo;
    private Money totalAmounts;
    private OrderState state;
    private LocalDateTime orderedAt;

    /* Constructor */
    private PlaceOrderCommandResult() {}

    /* Static Factory Method */
    public static PlaceOrderCommandResult from(OrderEntity orderEntity) {
        PlaceOrderCommandResult result = new PlaceOrderCommandResult();
        result.orderId = orderEntity.getId();
        result.orderer = orderEntity.getOrderer();
        result.orderProducts = orderEntity.getOrderProducts().stream()
                        .map(orderProductEntity -> OrderProduct.create(
                                orderProductEntity.getOrderProduct().getProductId(),
                                orderProductEntity.getOrderProduct().getPrice(),
                                orderProductEntity.getOrderProduct().getQuantity()))
                        .collect(Collectors.toList());
        result.shippingInfo = orderEntity.getShippingInfo();
        result.totalAmounts = orderEntity.getTotalAmounts();
        result.state = orderEntity.getState();
        result.orderedAt = orderEntity.getOrderedAt();
        return result;
    }
}
