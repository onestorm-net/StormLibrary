package net.onestorm.library.event;


import net.onestorm.library.event.build.EventHandlerBuilder;

import java.util.concurrent.CompletableFuture;

public interface EventManager<E> {

    EventHandlerBuilder<E> register(Class<E> eventClass);

    void unregisterBind(Object listener);

    CompletableFuture<E> callEvent(E event);

    void register(EventHandler<E> eventHandler);

    void unregister(EventHandler<E> eventHandler);

}
