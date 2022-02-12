package com.jaesay.completablefuture;

import com.jaesay.domain.*;
import com.jaesay.service.InventoryService;
import com.jaesay.service.ProductInfoService;
import com.jaesay.service.ProductService;
import com.jaesay.service.ReviewService;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.jaesay.util.CommonUtil.*;
import static com.jaesay.util.LoggerUtil.log;

public class ProductServiceUsingCompletableFuture {

    private final ProductInfoService productInfoService;
    private final ReviewService reviewService;
    private final InventoryService inventoryService;

    public ProductServiceUsingCompletableFuture(ProductInfoService productInfoService, ReviewService reviewService, InventoryService inventoryService) {
        this.productInfoService = productInfoService;
        this.reviewService = reviewService;
        this.inventoryService = inventoryService;
    }

    // productService
    //      -> a productInfoService call
    //      -> a reviewService call
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

    // productService
    //      -> a productInfoService call
    //          -> inventoryService calls
    //      -> a reviewService call
    public Product retrieveProductDetailsWithInventory(String productId) {
        startTimer();

        CompletableFuture<ProductInfo> productInfoCompletableFuture = CompletableFuture
                .supplyAsync(() -> productInfoService.retrieveProductInfo(productId))
                .thenApply(productInfo -> {
                    List<ProductOption> productOptions = updateInventory(productInfo); // blocking calls
                    productInfo.setProductOptions(productOptions); // 동기화 문제는 고려 X
                    return productInfo;
                });
        CompletableFuture<Review> reviewCompletableFuture = CompletableFuture.supplyAsync(() -> reviewService.retrieveReviews(productId));

        Product product = productInfoCompletableFuture
                .thenCombine(reviewCompletableFuture, (productInfo, review) -> new Product(productId, productInfo, review))
                .join();

        timeTaken();
        return product;
    }

    private List<ProductOption> updateInventory(ProductInfo productInfo) {
        return productInfo.getProductOptions().stream()
                .peek(productOption -> {
                    Inventory inventory = inventoryService.addInventory(productOption);
                    productOption.setInventory(inventory); // 동기화 문제는 고려 X
                })
                .collect(Collectors.toList());
    }

    public Product retrieveProductDetailsWithInventory_v2(String productId) {
        startTimer();

        CompletableFuture<ProductInfo> productInfoCompletableFuture = CompletableFuture
                .supplyAsync(() -> productInfoService.retrieveProductInfo(productId))
                .thenApply(productInfo -> {
                    List<ProductOption> productOptions = updateInventory_v2(productInfo); // blocking calls
                    productInfo.setProductOptions(productOptions); // 동기화 문제는 고려 X
                    return productInfo;
                });
        CompletableFuture<Review> reviewCompletableFuture = CompletableFuture.supplyAsync(() -> reviewService.retrieveReviews(productId));

        Product product = productInfoCompletableFuture
                .thenCombine(reviewCompletableFuture, (productInfo, review) -> new Product(productId, productInfo, review))
                .join();

        timeTaken();
        return product;
    }

    private List<ProductOption> updateInventory_v2(ProductInfo productInfo) {
        List<CompletableFuture<ProductOption>> completableFutures = productInfo.getProductOptions().stream()
                .map(productOption -> CompletableFuture.supplyAsync(() -> inventoryService.addInventory(productOption))
                        .thenApply(inventory -> {
                            productOption.setInventory(inventory); // 동기화 문제는 고려 X
                            return productOption;
                        }))
                .collect(Collectors.toList());

        return completableFutures.stream()
                .map(CompletableFuture::join) // 파이프라인에서 한개 씩 blocking 될 것 같지만 위에서 supplyAsync 로 CompletableFuture를 만들때 이미 백그라운드에서 실행
                .collect(Collectors.toList());
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
