package com.ddd.order.domain.service;

import com.ddd.order.domain.aggregate.Money;
import com.ddd.order.domain.aggregate.OrderProduct;
import com.ddd.order.infra.client.MemberDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service // 도메인 서비스
public class DiscountCalculationService {
    public Money calculateDiscountedTotalAmounts(List<OrderProduct> orderProducts) {
        Money totalDiscountedCouponAmounts = orderProducts.stream().map(OrderProduct::getDiscountedCouponAmounts)
                .reduce(new Money(BigDecimal.ZERO), Money::add);

        Money totalDiscountedMembershipAmounts = orderProducts.stream().map(OrderProduct::getDiscountedMembershipAmounts)
                .reduce(new Money(BigDecimal.ZERO), Money::add);

        return totalDiscountedCouponAmounts.add(totalDiscountedMembershipAmounts);
    }

    public Money calculateDiscountedMembershipAmounts(Money productAmounts, MemberDto.MemberGrade memberGrade) {
        switch (memberGrade) {
            case GOLD:
                return productAmounts.rate(new BigDecimal(3));
            case SILVER:
                return productAmounts.rate(new BigDecimal(2));
            case BRONZE:
                return productAmounts.rate(new BigDecimal(1));
            default:
                return new Money(BigDecimal.ZERO);
        }
    }

    // TODO 쿠폰 추가 후 수정
    public Money calculateDiscountedCouponAmounts(Money productAmounts) {
        return new Money(BigDecimal.ZERO);
    }
}
