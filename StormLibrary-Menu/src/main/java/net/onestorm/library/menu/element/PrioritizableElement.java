package net.onestorm.library.menu.element;

import java.util.OptionalInt;

public interface PrioritizableElement {

    OptionalInt getPriority();

    void setPriority(int priority);

}
