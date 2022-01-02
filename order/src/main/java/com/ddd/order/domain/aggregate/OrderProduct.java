package com.ddd.order.domain.aggregate;

import com.ddd.order.domain.service.DiscountCalculationService;
import com.ddd.order.infra.client.MemberDto;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.FIELD)
public class OrderProduct {
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "price")
    private Money price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "amounts")
    private Money amounts;

    @Column(name = "discounted_membership_amounts")
    private Money discountedMembershipAmounts;

    @Column(name = "discounted_coupon_amounts")
    private Money discountedCouponAmounts;

    /* Constructor */
    protected OrderProduct() {
    }

    public OrderProduct(Long productId, Money price, int quantity) {
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
        this.amounts = calculateAmounts();
    }

    /* Business Logic */
    private Money calculateAmounts() {
        return price.multiply(quantity);
    }

    protected void calculateDiscountedMembershipAmounts(DiscountCalculationService service, MemberDto.MemberGrade memberGrade) {
        this.discountedMembershipAmounts = service.calculateDiscountedMembershipAmounts(this.amounts, memberGrade);
    }

    protected void calculateDiscountedCouponAmounts(DiscountCalculationService service) {
        this.discountedCouponAmounts = service.calculateDiscountedCouponAmounts(this.amounts);
    }

    /* Getter */
    public Long getProductId() {
        return productId;
    }

    public Money getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Money getAmounts() {
        return amounts;
    }

    public Money getDiscountedMembershipAmounts() {
        return discountedMembershipAmounts;
    }

    public Money getDiscountedCouponAmounts() {
        return discountedCouponAmounts;
    }
}
