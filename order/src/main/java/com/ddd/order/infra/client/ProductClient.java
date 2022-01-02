package com.ddd.order.infra.client;

import com.ddd.order.domain.aggregate.Money;
import org.springframework.stereotype.Component;
import org.springframework.util.ConcurrentReferenceHashMap;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Component
public class ProductClient {
    ConcurrentReferenceHashMap<Long, ProductDto> productMap = new ConcurrentReferenceHashMap<>();

    @PostConstruct
    public void init() {
        productMap.put(1L, new ProductDto(1L, "상품1", new Money(new BigDecimal(1000))));
        productMap.put(2L, new ProductDto(2L, "상품2", new Money(new BigDecimal(2000))));
        productMap.put(3L, new ProductDto(3L, "상품3", new Money(new BigDecimal(3000))));
    }

    public ProductDto getProduct(Long productId) {
        return productMap.get(productId);
    }
}
