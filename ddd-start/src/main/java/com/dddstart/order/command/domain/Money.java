package com.dddstart.order.command.domain;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Getter @ToString
public class Money {
    private BigDecimal value;

    public Money(BigDecimal value) {
        this.value = value;
    }

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
}
