package org.jaesay.chapter02;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Chapter02Example {

    public static void main(String[] args) {
        Movie avatar = new Movie("아바타", Duration.ofMinutes(120), Money.wons(10_000),
                new AmountDiscountPolicy(
                        Money.wons(800),
                        new SequenceCondition(1),
                        new SequenceCondition(10),
                        new PeriodCondition(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(11, 59)),
                        new PeriodCondition(DayOfWeek.THURSDAY, LocalTime.of(10, 0), LocalTime.of(20, 59))
                )
        );

        Movie titanic = new Movie("타이타닉", Duration.ofMinutes(180), Money.wons(11_000),
                new PercentDiscountPolicy(
                        0.1,
                        new SequenceCondition(2),
                        new PeriodCondition(DayOfWeek.TUESDAY, LocalTime.of(14, 0), LocalTime.of(16, 59)),
                        new PeriodCondition(DayOfWeek.THURSDAY, LocalTime.of(10, 0), LocalTime.of(13, 59))
                )
        );

        Movie starWars = new Movie("스타워즈", Duration.ofMinutes(210), Money.wons(10_000), new NoneDiscountPolicy());

        Screening avatarScreening = new Screening(avatar, 10, LocalDateTime.of(2022, 2, 20, 10, 30));
        System.out.println(avatarScreening.getMovieFee());
        System.out.println(avatar.calculateMovieFee(avatarScreening));
    }
}
