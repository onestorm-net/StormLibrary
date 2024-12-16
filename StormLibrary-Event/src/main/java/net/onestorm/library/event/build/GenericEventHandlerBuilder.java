package net.onestorm.library.event.build;

import net.onestorm.library.event.EventHandler;
import net.onestorm.library.event.EventManager;
import net.onestorm.library.event.option.EventOption;

import java.util.function.Consumer;

public class GenericEventHandlerBuilder<E> implements EventHandlerBuilder<E> {

    private final EventManager<E> eventManager;
    private final Class<E> eventClass;
    private Object listener = null;


    public GenericEventHandlerBuilder(EventManager<E> eventManager, Class<E> eventClass) {
        this.eventManager = eventManager;
        this.eventClass = eventClass;
    }

    @Override
    public EventHandlerBuilder<E> bind(Object listener) {
        this.listener = listener;
        return this;
    }

    @Override
    public <T> EventHandlerBuilder<E> option(EventOption<T> option, T value) {
        // todo
        return this;
    }

    @Override
    public EventHandler<E> handler(Consumer<E> consumer) {
        // todo
        return null;
    }
}
