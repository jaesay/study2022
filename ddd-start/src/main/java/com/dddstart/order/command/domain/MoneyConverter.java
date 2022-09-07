package com.dddstart.order.command.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.math.BigDecimal;

@Converter(autoApply = true)
public class MoneyConverter implements AttributeConverter<Money, Long> {

    @Override
    public Long convertToDatabaseColumn(Money money) {
        if (money == null) {
            return null;
        }
        return money.getValue().longValue();
    }

    @Override
    public Money convertToEntityAttribute(Long value) {
        if (value == null) {
            return null;
        }
        return new Money(new BigDecimal(value));
    }
}
