package com.jaesay.completablefuture;

import com.jaesay.service.HelloWorldService;
import com.jaesay.util.LoggerUtil;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.jaesay.util.CommonUtil.*;
import static com.jaesay.util.LoggerUtil.log;

public class SimpleCompletableFutureExampleUsingHelloWorld {

    private final HelloWorldService helloWorldService;

    public SimpleCompletableFutureExampleUsingHelloWorld(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    public static void main(String[] args) {
        HelloWorldService helloWorldService = new HelloWorldService();

        // default로 common fork-join pool에서 실행
        CompletableFuture.supplyAsync(helloWorldService::helloWorld) // background task를 트리거하고 즉시 응답 (1초 delay), responsive principal
                .thenAccept(LoggerUtil::log) // message driven principal, supplyAsync의 결과 던지면 thenAccept 엑션
                .join(); // 모든 작업이 끝날 때까지 caller thread를 block, join 호출 전까지는 main thread release

        log("Done");
//        delay(2000); // 1초가 지나기 전에 끝나기 때문에 2초 delay
    }

    public CompletableFuture<String> helloWorld() {
        return CompletableFuture.supplyAsync(helloWorldService::helloWorld)
                .thenApply(String::toUpperCase);
    }

    public String combine_2_async_calls() {
        // independent tasks
        CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(helloWorldService::hello);
        CompletableFuture<String> worldFuture = CompletableFuture.supplyAsync(helloWorldService::world);

        return helloFuture.thenCombine(worldFuture, (h, w) -> h + w)
                .thenApply(String::toUpperCase)
                .join();
    }

    public String combine_3_async_calls() {
        // independent tasks
        CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(helloWorldService::hello);
        CompletableFuture<String> worldFuture = CompletableFuture.supplyAsync(helloWorldService::world);
        CompletableFuture<String> hiFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return "Hi";
        });

        return helloFuture
                .thenCombine(worldFuture, (h, w) -> h + w)
                .thenCombine(hiFuture, (hw, h) -> hw + " " + h)
                .thenApply(String::toUpperCase)
                .join();
    }

    public String combine_3_async_calls_log() {
        // independent tasks
        CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(helloWorldService::hello);
        CompletableFuture<String> worldFuture = CompletableFuture.supplyAsync(helloWorldService::world);
        CompletableFuture<String> hiFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return "Hi";
        });

        return helloFuture
                .thenCombine(worldFuture, (h, w) -> {
                    log("inside thenCombine 1");
                    return h + w;
                })
                .thenCombine(hiFuture, (hw, h) -> {
                    log("inside thenCombine 2");
                    return hw + " " + h;
                })
                .thenApply(s -> {
                    log("inside thenApply");
                    return s.toUpperCase();
                })
                .join();
    }

    public String combine_3_async_calls_customThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(helloWorldService::hello, executorService);
        CompletableFuture<String> worldFuture = CompletableFuture.supplyAsync(helloWorldService::world, executorService);
        CompletableFuture<String> hiFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return "Hi";
        }, executorService);

        // Whole pipeline이 custom thread pool에서 동작
        // thenCombine, thenApply 등도 custom thread pool에서 동작
        return helloFuture
                .thenCombine(worldFuture, (h, w) -> {
                    log("inside thenCombine 1");
                    return h + w;
                })
                .thenCombine(hiFuture, (hw, h) -> {
                    log("inside thenCombine 2");
                    return hw + " " + h;
                })
                .thenApply(s -> {
                    log("inside thenApply");
                    return s.toUpperCase();
                })
                .join();
    }

    public CompletableFuture<String> helloWorld_thenCombine() {
        return CompletableFuture.supplyAsync(helloWorldService::hello)
                .thenCompose(helloWorldService::worldFuture) // 응답 받은 후 실행 (dependent task)
                .thenApply(String::toUpperCase);
    }
}
