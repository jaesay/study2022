package com.jaesay.completablefuture;

import com.jaesay.domain.Product;
import com.jaesay.service.InventoryService;
import com.jaesay.service.ProductInfoService;
import com.jaesay.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceUsingCompletableFutureExceptionTest {

    @Mock
    ProductInfoService productInfoService;

    @Mock
    ReviewService reviewService;

    @Mock
    InventoryService inventoryService;

    @InjectMocks
    ProductServiceUsingCompletableFuture service;

    @Test
    void retrieveProductDetailsWithInventory_v2_reviewServiceError() {
        // given
        String productId = "ABC123";
        when(productInfoService.retrieveProductInfo(any())).thenCallRealMethod();
        when(inventoryService.addInventory(any())).thenCallRealMethod();
        when(reviewService.retrieveReviews(any())).thenThrow(new RuntimeException("Exception Occurred"));

        // when
        Product product = service.retrieveProductDetailsWithInventory_v2(productId);

        // then
        assertNotNull(product);
        assertNotNull(product.getProductInfo());
        product.getProductInfo().getProductOptions()
                .forEach(productOption -> assertNotNull(productOption.getInventory()));
        assertNotNull(product.getReview());
        assertEquals(0, product.getReview().getNoOfReviews());
    }

    @Test
    void retrieveProductDetailsWithInventory_v2_productServiceError() {
        // given
        String productId = "ABC123";
        when(productInfoService.retrieveProductInfo(any())).thenThrow(new RuntimeException("Exception Occurred"));
        when(reviewService.retrieveReviews(any())).thenCallRealMethod();

        // then
        assertThrows(RuntimeException.class, () -> service.retrieveProductDetailsWithInventory_v2(productId));
    }

    @Test
    void retrieveProductDetailsWithInventory_v2_inventoryServiceError() {
        // given
        String productId = "ABC123";
        when(productInfoService.retrieveProductInfo(any())).thenCallRealMethod();
        when(inventoryService.addInventory(any())).thenThrow(new RuntimeException("Exception Occurred"));
        when(reviewService.retrieveReviews(any())).thenCallRealMethod();

        // when
        Product product = service.retrieveProductDetailsWithInventory_v2(productId);

        // then
        assertNotNull(product);
        assertNotNull(product.getProductInfo());
        product.getProductInfo().getProductOptions()
                .forEach(productOption -> {
                    assertNotNull(productOption.getInventory());
                    assertEquals(0, productOption.getInventory().getCount());
                });
        assertNotNull(product.getReview());
    }
}