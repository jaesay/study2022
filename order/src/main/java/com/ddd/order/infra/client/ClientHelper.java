package com.ddd.order.infra.client;

import org.springframework.stereotype.Component;

@Component
public class ClientHelper {
    private final MemberClient memberClient;
    private final ProductClient productClient;

    public ClientHelper(MemberClient memberClient, ProductClient productClient) {
        this.memberClient = memberClient;
        this.productClient = productClient;
    }

    public MemberDto findMember(long memberId) {
        MemberDto member = memberClient.getMember(memberId);
        if (member == null) {
            throw new RuntimeException("member not found");
        }
        return member;
    }

    public ProductDto findProduct(long productId) {
        ProductDto product = productClient.getProduct(productId);
        if (product == null) {
            throw new RuntimeException("product not found");
        }
        return product;
    }
}
