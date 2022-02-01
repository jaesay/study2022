package com.jaesay.chapter6;

import java.util.List;
import java.util.concurrent.*;

public class FutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Callable<String> helloCallable = () -> {
            Thread.sleep(2000L);
            return "hello";
        };

        Future<String> helloFuture = executorService.submit(helloCallable);

        System.out.println("Started");
        System.out.println(helloFuture.isDone());
//        helloFuture.cancel(false);
        helloFuture.get(); // blocking call
        System.out.println(helloFuture.isDone());
        System.out.println("Ended");

        Callable<String> task1 = () -> {
            Thread.sleep(1000L);
            return "task1";
        };

        Callable<String> task2 = () -> {
            Thread.sleep(2000L);
            return "task2";
        };

        Callable<String> task3 = () -> {
            Thread.sleep(3000L);
            return "task3";
        };
//        List<Future<String>> futures = executorService.invokeAll(List.of(task1, task2, task3));
//        for (var future : futures) {
//            System.out.println(future.get());
//        }
        String anyTask = executorService.invokeAny(List.of(task1, task2, task3));
        System.out.println(anyTask);

        executorService.shutdown();



    }
}
