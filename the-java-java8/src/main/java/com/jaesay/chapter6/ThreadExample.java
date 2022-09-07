package com.jaesay.chapter6;

public class ThreadExample {
    public static void main(String[] args) {
        // thread 처리가 너무 복잡
        Thread thread = new Thread(() -> {
            System.out.println("Thread : " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        });
        thread.start();

        System.out.println("Hello : " + Thread.currentThread().getName());
//        thread.interrupt(); // thread를 꺠움
        try {
            thread.join(); // thread가 끝날때까지 기다림
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread + " is finished");
    }
}
