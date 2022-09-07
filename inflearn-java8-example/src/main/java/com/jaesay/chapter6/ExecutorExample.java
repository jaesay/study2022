package com.jaesay.chapter6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorExample {
    public static void main(String[] args) {
        // thread 관리
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // runnable의 return 값이 void 임 => callable, future
        executorService.submit(() -> System.out.println("task1 :" + Thread.currentThread().getName()));
        executorService.submit(() -> System.out.println("task2 :" + Thread.currentThread().getName()));
        executorService.submit(() -> System.out.println("task3 :" + Thread.currentThread().getName()));
        executorService.submit(() -> System.out.println("task4 :" + Thread.currentThread().getName()));

        executorService.shutdown(); // graceful shutdown
    }
}
