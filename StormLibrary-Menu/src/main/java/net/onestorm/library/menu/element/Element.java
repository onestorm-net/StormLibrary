package net.onestorm.library.menu.element;


import net.onestorm.library.menu.Menu;
import net.onestorm.library.menu.cell.Cell;

import java.util.Collection;
import java.util.Map;

public interface Element {

    String getName();

    Iterable<Cell> getContent(Menu menu);

}
