package org.jaesay.chapter09;

// 할인 요금을 계산할 책임을 그대로 DiscountPolicy 계층에 유지시킬 수 있다.
public class NoneDiscountPolicy extends DiscountPolicy {

    // 실제 이 메소드는 할인조건이 없기 때문에 호출되지 않는다. 고민하고 트레이드오프하자
    @Override
    protected Money getDiscountAmount(Screening screening) {
        return Money.ZERO;
    }
}
