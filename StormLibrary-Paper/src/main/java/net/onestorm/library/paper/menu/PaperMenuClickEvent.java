package net.onestorm.library.paper.menu;

import net.onestorm.library.menu.Menu;
import net.onestorm.library.menu.MenuClickEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class PaperMenuClickEvent implements MenuClickEvent<ItemStack> {

    private final InventoryClickEvent event;
    private final Menu menu;

    public PaperMenuClickEvent(InventoryClickEvent event, Menu menu) {
        this.event = event;
        this.menu = menu;
    }

    @Override
    public ItemStack getCursorItem() {
        return event.getCursor();
    }

    @Override
    public ItemStack getClickedItem() {
        return event.getCurrentItem();
    }

    @Override
    public void setClickedItem(ItemStack item) {
        event.setCurrentItem(item);
    }

    @Override
    public int getSlot() {
        return event.getSlot(); // todo add support for top and bottom inventory clicks
    }

    @Override
    public Menu getMenu() {
        return menu;
    }
}
