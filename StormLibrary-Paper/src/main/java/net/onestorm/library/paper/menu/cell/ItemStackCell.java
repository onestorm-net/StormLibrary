package net.onestorm.library.paper.menu.cell;

import net.onestorm.library.menu.cell.Cell;
import org.bukkit.inventory.ItemStack;

public interface ItemStackCell extends Cell {

    ItemStack getItemStack();

    void setItemStack(ItemStack itemStack);

    void updateItemStack(ItemStack itemStack);

}
