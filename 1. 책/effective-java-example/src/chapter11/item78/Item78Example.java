package chapter11.item78;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

public class Item78Example {

    private static volatile int nextSerialNumber = 0;
    private static final AtomicLong nextSerialNumber2 = new AtomicLong();

    private static int generateSerialNumber() {
        return nextSerialNumber++;
    }

    private static long generateSerialNumber2() {
        return nextSerialNumber2.getAndIncrement();
    }

    public static void main(String[] args) {
        IntStream.rangeClosed(1, 100_000).forEach(i -> CompletableFuture.runAsync(() -> generateSerialNumber()));
        System.out.println("nextSerialNumber = " + nextSerialNumber);

        IntStream.rangeClosed(1, 100_000).forEach(i -> CompletableFuture.runAsync(() -> generateSerialNumber2()));
        System.out.println("nextSerialNumber2 = " + nextSerialNumber2);
    }
}
