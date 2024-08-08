package net.onestorm.library.menu.element;

import net.onestorm.library.menu.Menu;

public interface CloseableElement {

    /**
     * Executes when a menu containing this element is closed.
     *
     * @param menu the menu that is closing
     */
    void close(Menu menu);
}
