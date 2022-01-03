package com.ddd.order.domain.service;

import com.ddd.order.domain.model.Money;
import com.ddd.order.domain.model.OrderEntity;
import com.ddd.order.infra.client.MemberClient;
import com.ddd.order.infra.client.MemberDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service // 도메인 서비스
public class DiscountCalculationService {
    private final MemberClient memberClient;
//    private final CouponClient couponClient;

    public DiscountCalculationService(MemberClient memberClient) {
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
