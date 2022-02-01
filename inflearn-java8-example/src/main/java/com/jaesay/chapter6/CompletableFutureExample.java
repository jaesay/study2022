package com.jaesay.chapter6;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class CompletableFutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = new CompletableFuture<>();
        future.complete("jaeseong");
        System.out.println(future.get());

        CompletableFuture<String> future2 = CompletableFuture.completedFuture("jaeseong");
        System.out.println(future2.get());

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("task1 : " + Thread.currentThread().getName());
        });
        voidCompletableFuture.get();

        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("task2 : " + Thread.currentThread().getName());
            return "task2";
        });
        stringCompletableFuture.get();

        // callback 정의가 가능해짐
        System.out.println("thenApply");
        CompletableFuture<String> task3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task3 : " + Thread.currentThread().getName());
            return "task3";
        }).thenApply(s -> {
            System.out.println("task3 thenApply : " + Thread.currentThread().getName());
            return s.toUpperCase();
        });

        System.out.println("task3.get() = " + task3.get());

        System.out.println("thenAccept");
        CompletableFuture<Void> task4 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task4 : " + Thread.currentThread().getName());
            return "task4";
        }).thenAccept(s -> {
            System.out.println("task4 thenApply : " + Thread.currentThread().getName());
        });
        task4.get();

        System.out.println("thenRun");
        CompletableFuture<Void> task5 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task5 : " + Thread.currentThread().getName());
            return "task5";
        }).thenRun(() -> {
            System.out.println("task5 thenRun : " + Thread.currentThread().getName());
        });
        task5.get();

        // 기본적으로는 ForkJoinPool의 common pool을 사용
        // executorService를 넘겨줄수도 있음
        System.out.println("==================");
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CompletableFuture<Void> task6 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task6 : " + Thread.currentThread().getName());
            return "task6";
        }, executorService).thenRunAsync(() -> {
            System.out.println("task6 thenRun : " + Thread.currentThread().getName());
        }, executorService);
        task6.get();

        executorService.shutdown();

        // 두 future를 연결할 수 있음
        // A => A 결괏값과 함꼐 B
        CompletableFuture<String> hello = getHello();
        CompletableFuture<String> helloWorld = hello.thenCompose(CompletableFutureExample::getWorld);
        System.out.println("helloWorld.get() = " + helloWorld.get());

        // A, B 동시 실행 => 어떤 처리
        CompletableFuture<String> helloWorld2 = hello.thenCombine(getWorld(), (h, w) -> h + " " + w);
        System.out.println("helloWorld2.get() = " + helloWorld2.get());

        // 같은 결과 타입의 두개 이상 동시 실행
        List<CompletableFuture<String>> futures = List.of(getHello(), getWorld());
        CompletableFuture[] futureArray = futures.toArray(new CompletableFuture[futures.size()]);
        CompletableFuture<Void> results = CompletableFuture.allOf(futureArray).thenAccept(System.out::println); // null
        System.out.println("results.get() = " + results.get()); // null

        CompletableFuture<List<String>> results2 = CompletableFuture.allOf(futureArray)
                .thenApply(v -> futures.stream() // v = null
                        .map(CompletableFuture::join) // unchecked exception
                        .collect(Collectors.toList()));

        results2.get().forEach(System.out::println);

        // 결과 하나
        CompletableFuture<Void> result = CompletableFuture.anyOf(getHello(), getWorld()).thenAccept(System.out::println);
        result.get();

        // 에러처리
        CompletableFuture<Object> errorFuture = CompletableFuture.supplyAsync(() -> {
            throw new IllegalArgumentException();
        }).exceptionally(ex -> {
            System.out.println(ex);
            return "Error";
        });
        System.out.println(errorFuture.get());

        CompletableFuture<Object> errorFuture2 = CompletableFuture.supplyAsync(() -> {
            throw new IllegalArgumentException();
        }).handle((r, ex) -> {
            if (ex != null) {
                System.out.println(ex);
                return "Error";
            }
            return r;
        });
        System.out.println(errorFuture2.get());
    }

    private static CompletableFuture<String> getWorld(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("world : " + Thread.currentThread().getName());
            return message + " world";
        });
    }

    private static CompletableFuture<String> getWorld() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("world : " + Thread.currentThread().getName());
            return "world";
        });
    }

    private static CompletableFuture<String> getHello() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("hello : " + Thread.currentThread().getName());
            return "hello";
        });
    }
}
