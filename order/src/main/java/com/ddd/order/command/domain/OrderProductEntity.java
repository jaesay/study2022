package com.ddd.order.command.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Getter
@ToString(exclude = "order")
public class OrderProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_product_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @Embedded
    private OrderProduct orderProduct;

    /* Constructor */
    protected OrderProductEntity() {}

    /* Static Factory Method */
    public static OrderProductEntity from(OrderProduct orderProduct) {
        OrderProductEntity orderProductEntity = new OrderProductEntity();
        orderProductEntity.orderProduct = orderProduct;
        return orderProductEntity;
    }

    /* Relationship Method */
    void setOrder(OrderEntity order) {
        this.order = order;
    }
}
