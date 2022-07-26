package chapter8.item52;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Item52Example {
    public static void main(String[] args) {
        new Thread(System.out::println).start();

        ExecutorService executorService = Executors.newFixedThreadPool(1);
//        executorService.submit(System.out::println);
    }
}
