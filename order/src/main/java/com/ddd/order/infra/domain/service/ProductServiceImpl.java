package com.ddd.order.infra.domain.service;

import com.ddd.order.domain.model.OrderProduct;
import com.ddd.order.domain.service.ProductService;
import com.ddd.order.domain.support.annotation.DomainService;
import com.ddd.order.infra.client.ProductClient;
import com.ddd.order.infra.client.ProductDto;

// TODO 이름 Impl 말고 다른거 없는지?
@DomainService // 도메인 서비스: 외부 시스템의 모델과 현재 도메인 모델간의 변환을 책임
public class ProductServiceImpl implements ProductService {
    private final ProductClient productClient;

    public ProductServiceImpl(ProductClient productClient) {
        this.productClient = productClient;
    }

    @Override
    public OrderProduct findOrderProduct(long productId, int quantity) {
        ProductDto product = productClient.getProduct(productId);
        if (product == null) {
            throw new RuntimeException("product not found");
        }
        return toOrderProduct(product, quantity);
    }

    // 복잡할 시 mapper/translator 등 별도 클래스 만들고 변환 처리
    private OrderProduct toOrderProduct(ProductDto product, int quantity) {
        return OrderProduct.create(product.getId(), product.getPrice(), quantity);
    }
}
