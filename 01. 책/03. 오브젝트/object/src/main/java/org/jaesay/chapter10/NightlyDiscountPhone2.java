package org.jaesay.chapter10;

import java.time.Duration;

// 상속은 결합도를 높인다. 내부 구현을 파악해야 하기 떄문에 이해하기 어렵다.
public class NightlyDiscountPhone2 extends Phone {

    private static final int LATE_NIGHT_HOUR = 22;

    private Money nightlyAmount;

    public NightlyDiscountPhone2(Money regularAmount, Duration seconds, Money nightlyAmount) {
        super(regularAmount, seconds);
        this.nightlyAmount = nightlyAmount;
    }

    @Override
    public Money calculateFee() {
        Money result = super.calculateFee();

        Money nightlyFee = Money.ZERO;
        for (Call call : getCalls()) {
            if (call.getFrom().getHour() >= LATE_NIGHT_HOUR) {
                nightlyFee = nightlyFee.plus(getAmount().minus(nightlyAmount).times(call.getDuration().getSeconds() / getSeconds().getSeconds()));
            }
        }

        return result.minus(nightlyAmount);
    }
}
