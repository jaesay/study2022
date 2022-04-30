package com.ddd.domainevent;

import org.springframework.transaction.event.TransactionalEventListener;

public interface AggregateEventHandler {

    @TransactionalEventListener
    void handleEvent(DomainEvent event);
}
