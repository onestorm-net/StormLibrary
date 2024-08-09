package net.onestorm.library.paper.menu.element;

import net.onestorm.library.menu.Menu;
import net.onestorm.library.menu.cell.Cell;
import net.onestorm.library.menu.element.Element;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemStackElement implements Element {

    private static final String ELEMENT_NAME = "stormlibrary:item-stack";

    private List<Integer> slots;
    private ItemStack itemStack;

    public ItemStackElement(List<Integer> slots, ItemStack itemStack) {
        this.slots = slots;
        this.itemStack = itemStack;
    }

    @Override
    public String getName() {
        return ELEMENT_NAME;
    }

    @Override
    public Iterable<Cell> getContent(Menu menu) {
        ElementContent content = new ElementContent(menu);

        for (int slot : slots) {
            content.setItem(slot, itemStack);
        }

        return content;
    }

    public List<Integer> getSlots() {
        return slots;
    }

    public void setSlots(List<Integer> slots) {
        this.slots = slots;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }
}
