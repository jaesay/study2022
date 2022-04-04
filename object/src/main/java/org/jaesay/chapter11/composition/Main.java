package org.jaesay.chapter11.composition;

import java.time.Duration;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        Phone phone = new Phone(new RegularPolicy(Money.wons(10), Duration.ofSeconds(10)));
        calls(phone);
        System.out.println("phone.calculateFee() = " + phone.calculateFee());

        Phone phone2 = new Phone(new NightlyDiscountPolicy(Money.wons(5), Money.wons(10), Duration.ofSeconds(10)));
        calls(phone2);
        System.out.println("phone2.calculateFee() = " + phone2.calculateFee());

        Phone phone3 = new Phone(new TaxablePolicy(0.05, new RegularPolicy(Money.wons(10), Duration.ofSeconds(10))));
        calls(phone3);
        System.out.println("phone3.calculateFee() = " + phone3.calculateFee());
    }

    private static void calls(Phone phone) {
        phone.call(new Call(
                LocalDateTime.of(2018, 1, 1, 12, 10, 0),
                LocalDateTime.of(2018, 1, 1, 12, 11, 0)
        ));
        phone.call(new Call(
                LocalDateTime.of(2018, 1, 2, 22, 10, 0),
                LocalDateTime.of(2018, 1, 2, 22, 11, 0)
        ));
    }
}
