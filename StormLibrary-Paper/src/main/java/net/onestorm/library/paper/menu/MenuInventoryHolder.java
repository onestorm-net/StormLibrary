package net.onestorm.library.paper.menu;

import net.kyori.adventure.text.Component;
import net.onestorm.library.menu.Menu;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public class MenuInventoryHolder implements InventoryHolder {

    private static final int MENU_WIDTH = 9;

    private final int height;
    private final Component title;
    private final Menu menu;

    private boolean isValid = true;

    public MenuInventoryHolder(int height, Component title, Menu menu) {
        this.height = height;
        this.title = title;
        this.menu = menu;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return Bukkit.createInventory(this, height * MENU_WIDTH, title);
    }
    public Menu getMenu() {
        return menu;
    }

    public void invalidate() {
        isValid = false;
    }

    public boolean isValid() {
        return isValid;
    }
}
