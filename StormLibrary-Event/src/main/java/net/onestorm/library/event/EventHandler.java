package net.onestorm.library.event;

import net.onestorm.library.event.option.EventOption;

public interface EventHandler<E> {

    Class<? extends E> getEventClass();

    <T> void setOption(EventOption<T> option, T value);

    <T> T getOption(EventOption<T> option);

    <T> T getOptionOrDefault(EventOption<T> option);

    void setListener(Object listener);

    Object getListener();

    void handle(E event);

}
