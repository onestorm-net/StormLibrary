package net.onestorm.library.paper.menu.cell;

import net.onestorm.library.menu.Menu;
import org.bukkit.inventory.ItemStack;

public class ItemStackCellImpl implements ItemStackCell {
    private final Menu menu;
    private final int index;

    private ItemStack itemStack;

    public ItemStackCellImpl(Menu menu, int index, ItemStack itemStack) {
        this.menu = menu;
        this.index = index;
        this.itemStack = itemStack;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public ItemStack getItemStack() {
        return itemStack;
    }

    @Override
    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    @Override
    public void update() {
        menu.updateCell(this);
    }

    @Override
    public void updateItemStack(ItemStack itemStack) {
        setItemStack(itemStack);
        update();
    }
}
