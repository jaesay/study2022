package com.jaesay.completablefuture;

import com.jaesay.service.HelloWorldService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static com.jaesay.util.CommonUtil.startTimer;
import static com.jaesay.util.CommonUtil.timeTaken;
import static org.junit.jupiter.api.Assertions.*;

class SimpleCompletableFutureExampleUsingHelloWorldTest {

    HelloWorldService helloWorldService = new HelloWorldService();
    SimpleCompletableFutureExampleUsingHelloWorld example = new SimpleCompletableFutureExampleUsingHelloWorld(helloWorldService);

    @Test
    void helloWorld() {
        //given
        startTimer();

        // when
        CompletableFuture<String> completableFuture = example.helloWorld();

        // then
        completableFuture
                .thenAccept(result -> assertEquals("HELLO WORLD", result))
                .join(); // join을 해야 backgound task(테스트)가 끝나기 전에 종료되지 않음

        timeTaken(); // 1
    }

    @Test
    void combine_2_async_calls() {
        //given
        startTimer();

        // when
        String result = example.combine_2_async_calls();

        // then
        assertEquals("HELLO WORLD!", result);
        timeTaken(); // 1
    }

    @Test
    void combine_3_async_calls() {
        //given
        startTimer();

        // when
        String result = example.combine_3_async_calls();

        // then
        assertEquals("HELLO WORLD! HI", result);
        timeTaken(); // 1
    }

    @Test
    void helloWorld_thenCombine() {
        //given
        startTimer();

        // when
        CompletableFuture<String> completableFuture = example.helloWorld_thenCombine();

        // then
        completableFuture
                .thenAccept(result -> assertEquals("HELLO WORLD!", result))
                .join();

        timeTaken(); // 2
    }

    @Test
    void combine_3_async_calls_log() {
        //given
        startTimer();

        // when
        String result = example.combine_3_async_calls_log();

        // then
        assertEquals("HELLO WORLD! HI", result);
        timeTaken();
    }

    @Test
    void combine_3_async_calls_log_async() {
        //given
        startTimer();

        // when
        String result = example.combine_3_async_calls_log_async();

        // then
        assertEquals("HELLO WORLD! HI", result);
        timeTaken();
    }

    @Test
    void combine_3_async_calls_customThreadPool() {
        //given
        startTimer();

        // when
        String result = example.combine_3_async_calls_customThreadPool();

        // then
        assertEquals("HELLO WORLD! HI", result);
        timeTaken();
    }

    @Test
    void combine_3_async_calls_customThreadPool_async() {
        //given
        startTimer();

        // when
        String result = example.combine_3_async_calls_customThreadPool_async();

        // then
        assertEquals("HELLO WORLD! HI", result);
        timeTaken();
    }

    @Test
    void combine_3_async_calls_customThreadPool_async_2() {
        //given
        startTimer();

        // when
        String result = example.combine_3_async_calls_customThreadPool_async_2();

        // then
        assertEquals("HELLO WORLD! HI", result);
        timeTaken();
    }
}