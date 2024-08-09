package net.onestorm.library.paper.menu.element;

import net.onestorm.library.menu.Menu;
import net.onestorm.library.menu.cell.Cell;
import net.onestorm.library.menu.element.Element;
import net.onestorm.library.menu.element.IdentifiableElement;
import net.onestorm.library.menu.element.PrioritizableElement;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Optional;

public class ItemStackElement implements Element, IdentifiableElement, PrioritizableElement {

    private static final String ELEMENT_NAME = "stormlibrary:item-stack";

    private List<Integer> slots;
    private ItemStack itemStack;
    private String identifier = null;
    private int priority = 0;

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

    @Override
    public Optional<String> getIdentifier() {
        return Optional.ofNullable(identifier);
    }

    @Override
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public void setPriority(int priority) {
        this.priority = priority;
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
