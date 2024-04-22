package net.onestorm.library.menu.element;

import java.util.OptionalInt;

public interface Prioritizable {

    OptionalInt getPriority();

    void setPriority(int priority);

}
