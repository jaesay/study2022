package com.ddd.order.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
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
    public OrderProductEntity(OrderProduct orderProduct) {
        this.orderProduct = orderProduct;
    }

    /* Getter */
    public Long getId() {
        return id;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public OrderProduct getOrderProduct() {
        return orderProduct;
    }

    /* Relationship Method */
    void setOrder(OrderEntity order) {
        this.order = order;
    }
}
