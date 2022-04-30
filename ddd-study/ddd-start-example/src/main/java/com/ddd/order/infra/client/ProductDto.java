package com.ddd.order.infra.client;

import com.ddd.order.command.domain.Money;

public class ProductDto {
    private long id;
    private String name;
    private Money price;

    public ProductDto(long id, String name, Money price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }
}
