package net.onestorm.library.paper.event.option;

import net.onestorm.library.event.option.EventOption;
import org.bukkit.event.EventPriority;

public class PaperEventOption<T> extends EventOption<T> {

    public static final EventOption<EventPriority> PRIORITY = create("priority", EventPriority.NORMAL);
    public static final EventOption<Boolean> IGNORE_CANCELLED = create("ignore_cancelled", Boolean.FALSE);

    protected PaperEventOption(String name, T defaultValue) {
        super(name, defaultValue);
    }
}
