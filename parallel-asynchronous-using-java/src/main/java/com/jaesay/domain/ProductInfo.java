package com.jaesay.domain;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProductInfo {
    private String productId;
    private List<ProductOption> productOptions;
}
