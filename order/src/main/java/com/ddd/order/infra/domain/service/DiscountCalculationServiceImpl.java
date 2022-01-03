package com.ddd.order.infra.domain.service;

import com.ddd.order.domain.model.Money;
import com.ddd.order.domain.model.OrderEntity;
import com.ddd.order.domain.service.DiscountCalculationService;
import com.ddd.order.domain.support.annotation.DomainService;
import com.ddd.order.infra.client.MemberClient;
import com.ddd.order.infra.client.MemberDto;

import java.math.BigDecimal;

// TODO 이름 Impl 말고 다른거 없는지?
@DomainService // 도메인 서비스: 한 애그리거트에 넣기 애매한 도메인 기능을 도메인 서비스로 구현
public class DiscountCalculationServiceImpl implements DiscountCalculationService {
    private final MemberClient memberClient;
    // coupon client...

    public DiscountCalculationServiceImpl(MemberClient memberClient) {
        this.memberClient = memberClient;
    }

    public Money calculateDiscountAmounts(OrderEntity orderEntity) {
        MemberDto member = memberClient.getMember(orderEntity.getOrderer().getMemberId());
        if (member == null) {
            throw new RuntimeException("member not found");
        }

        Money couponDiscount = calculateDiscount();
        Money memberGradeDiscount = calculateDiscounts(orderEntity.getTotalAmounts(), member.getGrade());

        return couponDiscount.add(memberGradeDiscount);
    }

    private Money calculateDiscounts(Money totalAmounts, MemberDto.MemberGrade memberGrade) {
        switch (memberGrade) {
            case GOLD:
                return totalAmounts.rate(new BigDecimal(3));
            case SILVER:
                return totalAmounts.rate(new BigDecimal(2));
            case BRONZE:
                return totalAmounts.rate(new BigDecimal(1));
            default:
                return new Money(BigDecimal.ZERO);
        }
    }

    // TODO 쿠폰 추가 후 수정
    private Money calculateDiscount() {
        return new Money(BigDecimal.ZERO);
    }
}
