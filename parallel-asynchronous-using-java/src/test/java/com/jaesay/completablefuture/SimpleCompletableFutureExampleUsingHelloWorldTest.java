package com.jaesay.completablefuture;

import com.jaesay.service.HelloWorldService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static com.jaesay.util.CommonUtil.*;
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
        timeTaken(); // 1
        completableFuture
                .thenAccept(result -> assertEquals("HELLO WORLD", result))
                .join(); // join을 해야 backgound task(테스트)가 끝나기 전에 종료되지 않음

    }

    @Test
    void combine_2_async_calls() {
        //given
        startTimer();

        // when
        String result = example.combine_2_async_calls();

        // then
        timeTaken(); // 1
        assertEquals("HELLO WORLD!", result);
    }

    @Test
    void combine_3_async_calls() {
        //given
        startTimer();

        // when
        String result = example.combine_3_async_calls();

        // then
        timeTaken(); // 1
        assertEquals("HELLO WORLD! HI", result);
    }

    @Test
    void helloWorld_thenCombine() {
        //given
        startTimer();

        // when
        CompletableFuture<String> completableFuture = example.helloWorld_thenCombine();

        // then
        timeTaken(); // 2
        completableFuture
                .thenAccept(result -> assertEquals("HELLO WORLD!", result))
                .join();

    }

    @Test
    void combine_3_async_calls_log() {
        //given
        startTimer();

        // when
        String result = example.combine_3_async_calls_log();

        // then
        timeTaken();
        assertEquals("HELLO WORLD! HI", result);
    }

    @Test
    void combine_3_async_calls_log_async() {
        //given
        startTimer();

        // when
        String result = example.combine_3_async_calls_log_async();

        // then
        timeTaken();
        assertEquals("HELLO WORLD! HI", result);
    }

    @Test
    void combine_3_async_calls_customThreadPool() {
        //given
        startTimer();

        // when
        String result = example.combine_3_async_calls_customThreadPool();

        // then
        timeTaken();
        assertEquals("HELLO WORLD! HI", result);
    }

    @Test
    void combine_3_async_calls_customThreadPool_async() {
        //given
        startTimer();

        // when
        String result = example.combine_3_async_calls_customThreadPool_async();

        // then
        timeTaken();
        assertEquals("HELLO WORLD! HI", result);
    }

    @Test
    void combine_3_async_calls_customThreadPool_async_2() {
        //given
        startTimer();

        // when
        String result = example.combine_3_async_calls_customThreadPool_async_2();

        // then
        timeTaken();
        assertEquals("HELLO WORLD! HI", result);
    }

    @Test
    void helloWorld_anyOf() {
        //given
        startTimer();

        // when
        String result = example.helloWorld_anyOf();

        // then
        timeTaken();
        assertEquals("hello world", result);
    }
}