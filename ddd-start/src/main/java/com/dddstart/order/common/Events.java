package com.dddstart.order.common;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

@Deprecated
public class Events {
    private static final ThreadLocal<List<EventHandler<?>>> handlers =
            new ThreadLocal<>();
    private static final ThreadLocal<List<EventHandler<?>>> asyncHandlers =
            new ThreadLocal<>();
    private static final ThreadLocal<Boolean> publishing =
            ThreadLocal.withInitial(() -> Boolean.FALSE);

    private static ExecutorService executor;

    public static void init(ExecutorService executor) {
        Events.executor = executor;
    }

    public static void close() {
        if (executor != null) {
            executor.shutdown();
            try {
                executor.awaitTermination(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
            }
        }
    }

    public static void raise(Object event) {
        if (publishing.get()) return;

        try {
            publishing.set(Boolean.TRUE);

            List<EventHandler<?>> asyncEvtHandlers = asyncHandlers.get();
            if (asyncEvtHandlers != null) {
                for (EventHandler handler : asyncEvtHandlers) {
                    if (handler.canHandle(event)) {
                        // 호출한 서비스와 같은 트랜잭션에서 실행됨
                        executor.submit(() -> handler.handle(event));
                    }
                }
            }
            List<EventHandler<?>> eventHandlers = handlers.get();
            if (eventHandlers == null) return;
            for (EventHandler handler : eventHandlers) {
                if (handler.canHandle(event)) {
                    // 호출한 서비스와 다른 트랜잭션에서 실행됨
                    // 스프링의 트랜잭션 관리자는 보통 쓰레드를 이용해서 트랜잭션을 전파한다. 물론, 쓰레드가 아닌 다른 방식을 이용해서 트랜잭션을 전파할 수 있지만 일반적으로 사용하는 트랜잭션 관리자는 쓰레드를 이용해서 트랜잭션을 전판한다. 이런 이유로 다른 쓰레드에서 실행되는 두 메서드는 서로 다른 트랜잭션을 사용하게 된다.
                    handler.handle(event);
                }
            }
        } finally {
            publishing.set(Boolean.FALSE);
        }
    }

    public static void handle(EventHandler<?> handler) {
        if (publishing.get()) return;

        List<EventHandler<?>> eventHandlers = handlers.get();
        if (eventHandlers == null) {
            eventHandlers = new ArrayList<>();
            handlers.set(eventHandlers);
        }
        eventHandlers.add(handler);
    }

    public static void handleAsync(EventHandler<?> handler) {
        if (publishing.get()) return;

        List<EventHandler<?>> eventHandlers = asyncHandlers.get();
        if (eventHandlers == null) {
            eventHandlers = new ArrayList<>();
            asyncHandlers.set(eventHandlers);
        }
        eventHandlers.add(handler);
    }

    public static void reset() {
        if (!publishing.get()) {
            handlers.remove();
            asyncHandlers.remove();
        }
    }
}
