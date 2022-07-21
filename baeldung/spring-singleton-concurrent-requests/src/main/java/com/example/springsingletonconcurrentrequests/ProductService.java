package com.example.springsingletonconcurrentrequests;

import java.util.Optional;

public interface ProductService {
    Optional<Product> getProductById(int id);
}
