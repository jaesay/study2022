package org.jaesay.chapter11.inheritance;

import java.time.Duration;

public class RateDiscountableNightlyDiscountPhone extends NightlyDiscountPhone {

    private Money discountAmount;

    public RateDiscountableNightlyDiscountPhone(Money nightlyAmount, Money regularAmount, Duration seconds) {
        super(nightlyAmount, regularAmount, seconds);
    }

    @Override
    protected Money afterCalculated(Money fee) {
        return fee.minus(discountAmount);
    }
}
