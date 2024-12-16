package net.onestorm.library.event.option;

import java.util.HashMap;
import java.util.Map;

public class EventOption<T> {

    private static final Map<String, EventOption<Object>> OPTIONS = new HashMap<>();

    @SuppressWarnings("unchecked")
    protected static <T> EventOption<T> create(String name, T defaultValue) {
        return (EventOption<T>) OPTIONS.putIfAbsent(name, new EventOption<>(name, defaultValue));
    }

    @SuppressWarnings("unchecked")
    public static <T> EventOption<T> valueOf(String name) {
        return (EventOption<T>) OPTIONS.get(name);
    }

    private final String name;
    private final T defaultValue;

    protected EventOption(String name, T defaultValue) {
        this.name = name;
        this.defaultValue = defaultValue;
    }

    public String getName() {
        return name;
    }

    public T getDefaultValue() {
        return defaultValue;
    }
}
