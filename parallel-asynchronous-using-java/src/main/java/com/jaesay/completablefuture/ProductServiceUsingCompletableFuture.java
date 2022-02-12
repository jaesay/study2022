package com.jaesay.completablefuture;

import com.jaesay.domain.Product;
import com.jaesay.domain.ProductInfo;
import com.jaesay.domain.Review;
import com.jaesay.service.ProductInfoService;
import com.jaesay.service.ProductService;
import com.jaesay.service.ReviewService;

import java.util.concurrent.CompletableFuture;

import static com.jaesay.util.CommonUtil.*;
import static com.jaesay.util.LoggerUtil.log;

public class ProductServiceUsingCompletableFuture {

    private final ProductInfoService productInfoService;
    private final ReviewService reviewService;

    public ProductServiceUsingCompletableFuture(ProductInfoService productInfoService, ReviewService reviewService) {
        this.productInfoService = productInfoService;
        this.reviewService = reviewService;
    }

    public Product retrieveProductDetails(String productId) {
        startTimer();

        CompletableFuture<ProductInfo> productInfoCompletableFuture = CompletableFuture.supplyAsync(() -> productInfoService.retrieveProductInfo(productId));
        CompletableFuture<Review> reviewCompletableFuture = CompletableFuture.supplyAsync(() -> reviewService.retrieveReviews(productId));

        Product product = productInfoCompletableFuture
                .thenCombine(reviewCompletableFuture, (productInfo, review) -> new Product(productId, productInfo, review))
                .join(); // block the thread => CompletableFuture를 리턴하여 클라이언트에게 blocking call 책임을 넣기는 게 좋다

        timeTaken();
        return product;
    }

    public CompletableFuture<Product> retrieveProductDetails_v2(String productId) {
        CompletableFuture<ProductInfo> productInfoCompletableFuture = CompletableFuture.supplyAsync(() -> productInfoService.retrieveProductInfo(productId));
        CompletableFuture<Review> reviewCompletableFuture = CompletableFuture.supplyAsync(() -> reviewService.retrieveReviews(productId));

        return productInfoCompletableFuture
                .thenCombine(reviewCompletableFuture, (productInfo, review) -> new Product(productId, productInfo, review));
    }

    public static void main(String[] args) {

        ProductInfoService productInfoService = new ProductInfoService();
        ReviewService reviewService = new ReviewService();
        ProductService productService = new ProductService(productInfoService, reviewService);
        String productId = "ABC123";
        Product product = productService.retrieveProductDetails(productId);
        log("Product is " + product);

    }
}
