package org.jaesay.chapter09.sl;

import org.jaesay.chapter09.AmountDiscountPolicy;
import org.jaesay.chapter09.Money;
import org.jaesay.chapter09.PeriodCondition;
import org.jaesay.chapter09.SequenceCondition;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

public class Client {
    public Money getAvatarFee() {
        ServiceLocator.provide(new AmountDiscountPolicy(
                Money.wons(800),
                new SequenceCondition(1),
                new SequenceCondition(10),
                new PeriodCondition(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(11, 59)),
                new PeriodCondition(DayOfWeek.THURSDAY, LocalTime.of(10, 0), LocalTime.of(20, 59))
        ));

        Movie avartar = new Movie("아바타", Duration.ofMinutes(120), Money.wons(10_000));
        return avartar.getFee();
    }
}
