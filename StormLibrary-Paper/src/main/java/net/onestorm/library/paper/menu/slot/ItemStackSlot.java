package net.onestorm.library.paper.menu.slot;

import net.onestorm.library.menu.slot.Slot;
import net.onestorm.library.paper.menu.PaperMenu;
import org.bukkit.inventory.ItemStack;

public class ItemStackSlot implements Slot {

    private final PaperMenu menu;
    private final int index;
    private ItemStack itemStack;

    public ItemStackSlot(PaperMenu menu, int index, ItemStack itemStack) {
        this.menu = menu;
        this.index = index;
        this.itemStack = itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void update() {
        menu.updateSlot(this);
    }
}
