package com.ddd.order.common;

import net.jodah.typetools.TypeResolver;

@Deprecated
public interface EventHandler<T> {
    void handle(T event);

    default boolean canHandle(Object event) {
        Class<?>[] typeArgs = TypeResolver.resolveRawArguments(
                EventHandler.class, this.getClass());
        return typeArgs[0].isAssignableFrom(event.getClass());
    }
}
