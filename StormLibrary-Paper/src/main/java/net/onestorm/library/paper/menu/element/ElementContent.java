package net.onestorm.library.paper.menu.element;

import net.onestorm.library.menu.Menu;
import net.onestorm.library.menu.cell.Cell;
import net.onestorm.library.paper.menu.cell.ItemStackCell;
import net.onestorm.library.paper.menu.cell.ItemStackCellImpl;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ElementContent implements Iterable<ItemStackCell> {

    private final Menu menu;
    private final Map<Integer, ItemStackCell> cellMap = new HashMap<>();

    public ElementContent(Menu menu) {
        this.menu = menu;
    }

    public Cell setItem(int index, ItemStack itemStack) {
        ItemStackCell cell = cellMap.get(index);

        if (cell == null) {
            cell = new ItemStackCellImpl(menu, index, itemStack);
            cellMap.put(index, cell);
        } else {
            cell.setItemStack(itemStack);
        }

        return cell;
    }

    public Cell setItem(int x, int y, ItemStack itemStack) {
        int index = calculateIndex(x, y, menu.getWidth());
        return setItem(index, itemStack);
    }

    private int calculateIndex(int x, int y, int width) {
        return y * width + x;
    }

    @NotNull
    @Override
    public Iterator<ItemStackCell> iterator() {
        return cellMap.values().iterator();
    }
}
