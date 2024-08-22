package net.onestorm.library.paper.menu;

import org.bukkit.event.inventory.InventoryType;

public class DispenserMenu extends PaperMenu {

    private static final int MENU_WIDTH = 3;
    private static final int MENU_HEIGHT = 3;
    private static final int MENU_SIZE = 9;

    @Override
    public int getWidth() {
        return MENU_WIDTH;
    }

    @Override
    public int getHeight() {
        return MENU_HEIGHT;
    }

    @Override
    public int getSize() {
        return MENU_SIZE;
    }

    @Override
    protected MenuHolder createMenuHolder() {
        String title = getTitle();
        if (title == null || title.isEmpty()) {
            return new MenuHolder(this, InventoryType.DISPENSER);
        }
        return new MenuHolder(this, InventoryType.DISPENSER, createTitle());
    }
}
