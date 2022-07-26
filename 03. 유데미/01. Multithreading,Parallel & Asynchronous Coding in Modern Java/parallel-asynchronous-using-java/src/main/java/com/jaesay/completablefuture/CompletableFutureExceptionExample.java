package com.jaesay.completablefuture;

import com.jaesay.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;

import static com.jaesay.util.CommonUtil.delay;
import static com.jaesay.util.LoggerUtil.log;

public class CompletableFutureExceptionExample {

    private final HelloWorldService helloWorldService;

    public CompletableFutureExceptionExample(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    public String combine_3_async_calls_handle() {
        // independent tasks
        CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(helloWorldService::hello);
        CompletableFuture<String> worldFuture = CompletableFuture.supplyAsync(helloWorldService::world);
        CompletableFuture<String> hiFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return "Hi";
        });

        return helloFuture
                .handle((r, e) -> {
                    log("Hello result is: " + r);
                    if (e != null) {
                        log("Hello Exception is : " + e.getMessage());
                        return ""; // recoverable value
                    }
                    return r;
                })
                .thenCombine(worldFuture, (h, w) -> h + w)
                .handle((r, e) -> {
                    log("World result is: " + r);
                    if (e != null) {
                        log("World Exception is : " + e.getMessage());
                        return ""; // recoverable value
                    }
                    return r;
                })
                .thenCombine(hiFuture, (hw, h) -> hw + " " + h)
                .thenApply(String::toUpperCase)
                .join();
    }

    public String combine_3_async_calls_exceptionally() {
        // independent tasks
        CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(helloWorldService::hello);
        CompletableFuture<String> worldFuture = CompletableFuture.supplyAsync(helloWorldService::world);
        CompletableFuture<String> hiFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return "Hi";
        });

        return helloFuture
                .exceptionally(e -> {
                    log("Hello Exception is : " + e.getMessage());
                    return ""; // recoverable value
                })
                .thenCombine(worldFuture, (h, w) -> h + w)
                .exceptionally(e -> {
                    log("World Exception is : " + e.getMessage());
                    return ""; // recoverable value
                })
                .thenCombine(hiFuture, (hw, h) -> hw + " " + h)
                .thenApply(String::toUpperCase)
                .join();
    }

    public String combine_3_async_calls_whenComplete() {
        // independent tasks
        CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(helloWorldService::hello);
        CompletableFuture<String> worldFuture = CompletableFuture.supplyAsync(helloWorldService::world);
        CompletableFuture<String> hiFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return "Hi";
        });

        return helloFuture
                .whenComplete((r, e) -> {
                    log("Hello result is: " + r);
                    if (e != null) {
                        log("Hello Exception is : " + e.getMessage());
                    }
                })
                .thenCombine(worldFuture, (h, w) -> h + w)
                .whenComplete((r, e) -> {
                    log("World result is: " + r);
                    if (e != null) {
                        log("World Exception is : " + e.getMessage());
                    }
                })
                .exceptionally(e -> {
                    log("Exception is : " + e.getMessage() + "in exceptionally()");
                    return ""; // recoverable value
                })
                .thenCombine(hiFuture, (hw, h) -> hw + " " + h)
                .thenApply(String::toUpperCase)
                .join();
    }
}
