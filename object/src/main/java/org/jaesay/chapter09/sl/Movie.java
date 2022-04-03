package org.jaesay.chapter09.sl;

import org.jaesay.chapter09.DiscountPolicy;
import org.jaesay.chapter09.Money;

import java.time.Duration;

public class Movie {
    private String title;
    private Duration runningTime;
    private Money fee;
    private DiscountPolicy discountPolicy;

    public Movie(String title, Duration runningTime, Money fee) {
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        discountPolicy = ServiceLocator.discountPolicy();
    }

    public Money getFee() {
        return fee;
    }
}
