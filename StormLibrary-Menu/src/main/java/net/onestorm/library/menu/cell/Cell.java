package net.onestorm.library.menu.cell;

import net.onestorm.library.menu.Menu;

public interface Cell {

    /**
     * Retrieves the index of the cell (also known as slots in Minecraft).
     *
     * @return the index of the cell
     */
    int getIndex();

    /**
     * Updates the current cell. Primarily intended for internal use.
     * This method triggers the {@link Menu#updateCell(Cell)} method.
     */
    void update();
}
