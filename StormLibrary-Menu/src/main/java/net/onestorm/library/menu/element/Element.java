package net.onestorm.library.menu.element;


import net.onestorm.library.menu.Menu;
import net.onestorm.library.menu.cell.Cell;

public interface Element {

    /**
     * Retrieves the name of the element.
     *
     * @return the name of the element
     */
    String getName();

    /**
     * Retrieves the content that needs to be rendered for this element.
     * This method is called during menu creation and updates.
     *
     * @param menu the menu that requests the content
     * @return an iterable of cells representing the rendered content
     */
    Iterable<Cell> getContent(Menu menu);
}
