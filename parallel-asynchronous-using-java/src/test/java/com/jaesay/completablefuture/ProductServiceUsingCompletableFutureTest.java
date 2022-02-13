package com.jaesay.completablefuture;

import com.jaesay.domain.Product;
import com.jaesay.service.InventoryService;
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
    InventoryService inventoryService = new InventoryService();
    ProductServiceUsingCompletableFuture service  = new ProductServiceUsingCompletableFuture(productInfoService, reviewService, inventoryService);

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

    @Test
    void retrieveProductDetailsWithInventory() {
        // given
        String productId = "ABC123";

        // when
        // 최소 inventory service 실행 시간 * inventory 개수(product option 개수) 만큼 시간이 더 걸린다. (blocking calls)
        Product product = service.retrieveProductDetailsWithInventory(productId);

        // then
        assertNotNull(product);
        assertNotNull(product.getProductInfo());
        product.getProductInfo().getProductOptions()
                .forEach(productOption -> assertNotNull(productOption.getInventory()));
        assertNotNull(product.getReview());
    }

    @Test
    void retrieveProductDetailsWithInventory_v2() {
        // given
        String productId = "ABC123";

        // when
        // 최소 inventory service 실행 시간만큼 추가로 더 걸린다.
        Product product = service.retrieveProductDetailsWithInventory_v2(productId);

        // then
        assertNotNull(product);
        assertNotNull(product.getProductInfo());
        product.getProductInfo().getProductOptions()
                .forEach(productOption -> assertNotNull(productOption.getInventory()));
        assertNotNull(product.getReview());
    }
}