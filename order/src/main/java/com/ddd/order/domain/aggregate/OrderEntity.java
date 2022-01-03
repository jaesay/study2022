package com.ddd.order.domain.aggregate;

import com.ddd.order.domain.service.DiscountCalculationService;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Access;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Access(AccessType.FIELD)
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;

    @Embedded
    private Orderer orderer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProductEntity> orderProducts = new ArrayList<>();

    @Column(name = "total_amounts")
    private Money totalAmounts;

    @Column(name = "payment_amounts")
    private Money paymentAmounts;

    @Embedded
    private ShippingInfo shippingInfo;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private OrderState state;

    @CreatedDate
    private LocalDateTime orderedAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    /* Constructor */
    protected OrderEntity() {
    }

    public OrderEntity(Orderer orderer, List<OrderProduct> orderProducts, ShippingInfo shippingInfo) {
        setOrderer(orderer);
        setOrderProducts(orderProducts);
        setShippingInfo(shippingInfo);
        this.state = OrderState.PAYMENT_WAITING;
    }

    /* Relationship Method */
    public void addOrderItem(OrderProductEntity orderProductEntity) {
        orderProducts.add(orderProductEntity);
        orderProductEntity.setOrder(this);
    }

    /* Setter */
    private void setOrderer(Orderer orderer) {
        if (orderer == null) throw new IllegalArgumentException("no orderer");
        this.orderer = orderer;
    }

    private void setOrderProducts(List<OrderProduct> orderProducts) {
        verifyAtLeastOneOrMoreOrderProducts(orderProducts);
        orderProducts.stream().map(OrderProductEntity::new).forEach(this::addOrderItem);
        calculateTotalAmounts();
    }

    private void setShippingInfo(ShippingInfo shippingInfo) {
        if (shippingInfo == null) throw new IllegalArgumentException("no shipping info");
        this.shippingInfo = shippingInfo;
    }

    private void verifyAtLeastOneOrMoreOrderProducts(List<OrderProduct> orderProducts) {
        if (orderProducts == null || orderProducts.isEmpty()) {
            throw new IllegalArgumentException("no order item");
        }
    }

    private void calculateTotalAmounts() {
        this.totalAmounts = new Money(orderProducts.stream()
                .map(orderProductEntity -> orderProductEntity.getOrderProduct().getAmounts().getValue())
                .reduce(BigDecimal.ZERO, BigDecimal::add));
    }

    public void calculatePaymentAmounts(DiscountCalculationService discountCalculationService) {
        Money discountAmounts = discountCalculationService.calculateDiscountAmounts(this);
        this.paymentAmounts = totalAmounts.minus(discountAmounts);
    }

    /* Getter */
    public Long getId() {
        return id;
    }

    public Orderer getOrderer() {
        return orderer;
    }

    public List<OrderProductEntity> getOrderProducts() {
        return orderProducts;
    }

    public Money getTotalAmounts() {
        return totalAmounts;
    }

    public Money getPaymentAmounts() {
        return paymentAmounts;
    }

    public ShippingInfo getShippingInfo() {
        return shippingInfo;
    }

    public OrderState getState() {
        return state;
    }

    public LocalDateTime getOrderedAt() {
        return orderedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
