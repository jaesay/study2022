package com.ddd.order.domain.model;

import com.ddd.order.domain.service.DiscountCalculationService;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@ToString(exclude = "orderProducts")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;

    @Version
    private long version;

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
    protected OrderEntity() {}

    /* Static Factory Method*/
    public static OrderEntity create(Orderer orderer, List<OrderProduct> orderProducts, ShippingInfo shippingInfo) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderer(orderer);
        orderEntity.setOrderProducts(orderProducts);
        orderEntity.setShippingInfo(shippingInfo);
        orderEntity.state = OrderState.PAYMENT_WAITING;
        return orderEntity;
    }

    /* Relationship Method */
    public void addOrderProducts(OrderProductEntity orderProductEntity) {
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
        orderProducts.stream().map(OrderProductEntity::from).forEach(this::addOrderProducts);
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

    public void startShipping() {
        verifyShippableState();
        this.state = OrderState.SHIPPED;
    }

    private void verifyShippableState() {
        verifyNotYetShipped();
        verifyNotCanceled();
    }

    private void verifyNotYetShipped() {
        if (!isNotYetShipped())
            throw new RuntimeException("order already shipped");
    }

    private boolean isNotYetShipped() {
        return state == OrderState.PAYMENT_WAITING || state == OrderState.PREPARING;
    }

    private void verifyNotCanceled() {
        if (state == OrderState.CANCELED) {
            throw new RuntimeException("order already canceled");
        }
    }

    public boolean matchVersion(long version) {
        return this.version == version;
    }

    public void cancel() {
        verifyNotYetShipped();
        this.state = OrderState.CANCELED;
    }
}
