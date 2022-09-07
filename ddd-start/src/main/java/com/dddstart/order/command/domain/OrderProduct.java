package com.dddstart.order.command.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
@ToString
public class OrderProduct {
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "price")
    private Money price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "amounts")
    private Money amounts;

    protected OrderProduct() {}

    public static OrderProduct create(Long productId, Money price, int quantity) {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.productId = productId;
        orderProduct.price = price;
        orderProduct.quantity = quantity;
        orderProduct.calculateAmounts();
        return orderProduct;
    }

    private void calculateAmounts() {
        this.amounts = price.multiply(quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProduct that = (OrderProduct) o;
        return quantity == that.quantity &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(price, that.price) &&
                Objects.equals(amounts, that.amounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, price, quantity, amounts);
    }
}
