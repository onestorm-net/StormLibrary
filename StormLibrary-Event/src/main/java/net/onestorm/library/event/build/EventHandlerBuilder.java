package net.onestorm.library.event.build;

import net.onestorm.library.event.EventHandler;
import net.onestorm.library.event.option.EventOption;

import java.util.function.Consumer;

public interface EventHandlerBuilder<E> {

    EventHandlerBuilder<E> bind(Object listener);

    <T> EventHandlerBuilder<E> option(EventOption<T> option, T value);

    EventHandler<E> handler(Consumer<E> consumer);

}
