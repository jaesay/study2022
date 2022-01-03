package com.ddd.order.infra.client;

import com.ddd.order.domain.model.Money;

public class ProductDto {
    private Long id;
    private String name;
    private Money price;

    public ProductDto(Long id, String name, Money price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }
}
