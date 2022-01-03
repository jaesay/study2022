package com.ddd.order.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

// TODO 코드 커지면 적절한 패키지 찾기
public class Money {
    private BigDecimal value;
//    private String currencyCode;

    /* Constructor */
    protected Money() {}

    public Money(BigDecimal value) {
        this.value = value;
    }

    /* Getter, Setter */
    public BigDecimal getValue() {
        return value;
    }

    /* Business Logic */
    public Money multiply(int multiplier) {
        return new Money(value.multiply(BigDecimal.valueOf(multiplier)));
    }

    public Money rate(BigDecimal rate) {
        return new Money(value.multiply(rate).divide(new BigDecimal(100), RoundingMode.FLOOR));
    }

    public Money add(Money money) {
        return new Money(value.add(money.getValue()));
    }

    public Money minus(Money money) {
        return new Money(value.subtract(money.value));
    }

    /* Override Method*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(value, money.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Money{" +
                "value=" + value +
                '}';
    }
}
