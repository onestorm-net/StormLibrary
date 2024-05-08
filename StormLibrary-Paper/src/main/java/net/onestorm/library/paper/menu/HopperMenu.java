package net.onestorm.library.paper.menu;

import org.bukkit.event.inventory.InventoryType;

public class HopperMenu extends PaperMenu {

    private static final int MENU_WIDTH = 5;
    private static final int MENU_HEIGHT = 1;
    private static final int MENU_SIZE = 5;

    public HopperMenu(String title) {
        setTitle(title);
    }

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
            return new MenuHolder(this, InventoryType.HOPPER);
        }
        return new MenuHolder(this, InventoryType.HOPPER, createTitle());
    }
}
