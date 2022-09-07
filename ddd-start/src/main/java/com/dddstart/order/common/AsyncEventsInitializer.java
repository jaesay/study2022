package com.dddstart.order.common;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.Executors;

@Deprecated
//@Component
public class AsyncEventsInitializer {

    @PostConstruct
    public void init() {
        Events.init(Executors.newFixedThreadPool(10));
    }

    @PreDestroy
    public void close() {
        Events.close();
    }
}
