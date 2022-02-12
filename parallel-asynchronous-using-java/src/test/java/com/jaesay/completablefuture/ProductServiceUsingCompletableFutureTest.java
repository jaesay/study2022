package com.jaesay.completablefuture;

import com.jaesay.domain.Product;
import com.jaesay.service.ProductInfoService;
import com.jaesay.service.ReviewService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static com.jaesay.util.CommonUtil.startTimer;
import static com.jaesay.util.CommonUtil.timeTaken;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceUsingCompletableFutureTest {

    ProductInfoService productInfoService = new ProductInfoService();
    ReviewService reviewService = new ReviewService();
    ProductServiceUsingCompletableFuture service  = new ProductServiceUsingCompletableFuture(productInfoService, reviewService);

    @Test
    void retrieveProductDetails() {
        // given
        String productId = "ABC123";

        // when
        Product product = service.retrieveProductDetails(productId);

        // then
        assertNotNull(product);
        assertNotNull(product.getProductInfo());
        assertNotNull(product.getReview());
    }

    @Test
    void retrieveProductDetails_v2() {
        // given
        String productId = "ABC123";

        // when
        startTimer();
        Product product = service.retrieveProductDetails_v2(productId).join();
        timeTaken();

        // then
        assertNotNull(product);
        assertNotNull(product.getProductInfo());
        assertNotNull(product.getReview());
    }
}