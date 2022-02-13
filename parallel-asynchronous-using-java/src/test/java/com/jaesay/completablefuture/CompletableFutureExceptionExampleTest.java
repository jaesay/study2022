package com.jaesay.completablefuture;

import com.jaesay.service.HelloWorldService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.jaesay.util.CommonUtil.startTimer;
import static com.jaesay.util.CommonUtil.timeTaken;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompletableFutureExceptionExampleTest {

    @Mock
    HelloWorldService helloWorldService = mock(HelloWorldService.class);

    @InjectMocks
    CompletableFutureExceptionExample example;

    @Test
    void combine_3_async_calls_handle() {
        // given
        startTimer();
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception Occurred"));
        when(helloWorldService.world()).thenCallRealMethod();

        // when
        String result = example.combine_3_async_calls_handle();

        // then
        assertEquals(" WORLD! HI", result);
        timeTaken();
    }

    @Test
    void combine_3_async_calls_handle_2() {
        // given
        startTimer();
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception Occurred"));
        when(helloWorldService.world()).thenThrow(new RuntimeException("Exception Occurred"));

        // when
        String result = example.combine_3_async_calls_handle();

        // then
        assertEquals(" HI", result);
        timeTaken();
    }

    @Test
    void combine_3_async_calls_handle_3() {
        // given
        startTimer();
        when(helloWorldService.hello()).thenCallRealMethod();
        when(helloWorldService.world()).thenCallRealMethod();

        // when
        String result = example.combine_3_async_calls_handle();

        // then
        assertEquals("HELLO WORLD! HI", result);
        timeTaken();
    }

    @Test
    void combine_3_async_calls_exceptionally() {
        // given
        startTimer();
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception Occurred"));
        when(helloWorldService.world()).thenCallRealMethod();

        // when
        String result = example.combine_3_async_calls_exceptionally();

        // then
        assertEquals(" WORLD! HI", result);
        timeTaken();
    }

    @Test
    void combine_3_async_calls_exceptionally_2() {
        // given
        startTimer();
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception Occurred"));
        when(helloWorldService.world()).thenThrow(new RuntimeException("Exception Occurred"));

        // when
        String result = example.combine_3_async_calls_exceptionally();

        // then
        assertEquals(" HI", result);
        timeTaken();
    }

    @Test
    void combine_3_async_calls_exceptionally_3() {
        // given
        startTimer();
        when(helloWorldService.hello()).thenCallRealMethod();
        when(helloWorldService.world()).thenCallRealMethod();

        // when
        String result = example.combine_3_async_calls_exceptionally();

        // then
        assertEquals("HELLO WORLD! HI", result);
        timeTaken();
    }

    @Test
    void combine_3_async_calls_whenComplete() {
        // given
        startTimer();
        when(helloWorldService.hello()).thenCallRealMethod();
        when(helloWorldService.world()).thenCallRealMethod();

        // when
        String result = example.combine_3_async_calls_whenComplete();

        // then
        assertEquals("HELLO WORLD! HI", result);
        timeTaken();
    }

    @Test
    void combine_3_async_calls_whenComplete_2() {
        // given
        startTimer();
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception Occurred"));
        when(helloWorldService.world()).thenThrow(new RuntimeException("Exception Occurred"));

        // when
        String result = example.combine_3_async_calls_whenComplete();

        // then
        assertEquals(" HI", result);
        timeTaken();
    }

    @Test
    void combine_3_async_calls_whenComplete_3() {
        // given
        startTimer();
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception Occurred"));
        when(helloWorldService.world()).thenCallRealMethod();

        // when
        String result = example.combine_3_async_calls_whenComplete();

        // then
        assertEquals(" HI", result);
        timeTaken();
    }

    @Test
    void combine_3_async_calls_whenComplete_4() {
        // given
        startTimer();
        when(helloWorldService.hello()).thenCallRealMethod();
        when(helloWorldService.world()).thenThrow(new RuntimeException("Exception Occurred"));

        // when
        String result = example.combine_3_async_calls_whenComplete();

        // then
        assertEquals(" HI", result);
        timeTaken();
    }
}