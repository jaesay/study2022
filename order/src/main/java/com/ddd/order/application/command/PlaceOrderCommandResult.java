package com.ddd.order.application.command;

import com.ddd.order.domain.aggregate.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PlaceOrderCommandResult {
    private Long orderId;
    private Orderer orderer;
    private List<OrderProduct> orderProducts;
    private ShippingInfo shippingInfo;
    private Money totalAmounts;
    private OrderState state;
    private LocalDateTime orderedAt;

    /* Constructor */
    private PlaceOrderCommandResult() {
    }

    /* Factory Method */
    public static PlaceOrderCommandResult from(OrderEntity orderEntity) {
        PlaceOrderCommandResult result = new PlaceOrderCommandResult();
        result.orderId = orderEntity.getId();
        result.orderer = orderEntity.getOrderer();
        result.orderProducts = orderEntity.getOrderProducts().stream()
                        .map(orderProductEntity -> new OrderProduct(
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

    /* Getter */
    public Long getOrderId() {
        return orderId;
    }

    public Orderer getOrderer() {
        return orderer;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public ShippingInfo getShippingInfo() {
        return shippingInfo;
    }

    public Money getTotalAmounts() {
        return totalAmounts;
    }

    public OrderState getState() {
        return state;
    }

    public LocalDateTime getOrderedAt() {
        return orderedAt;
    }
}
