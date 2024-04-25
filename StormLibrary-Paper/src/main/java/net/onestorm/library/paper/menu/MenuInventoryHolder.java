package net.onestorm.library.paper.menu;

import net.kyori.adventure.text.Component;
import net.onestorm.library.menu.Menu;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public class MenuInventoryHolder implements InventoryHolder {

    private static final int MENU_WIDTH = 9;

    private final PaperMenu menu;
    private final Inventory inventory;

    private boolean isValid = true;

    public MenuInventoryHolder(InventoryType type, Component title, PaperMenu menu) {
        this.menu = menu;
        if (type != InventoryType.HOPPER && type != InventoryType.DISPENSER) {
            throw new IllegalArgumentException("Invalid inventory type");
        }
        this.inventory = Bukkit.createInventory(this, type, title);
    }

    public MenuInventoryHolder(int height, Component title, PaperMenu menu) {
        this.menu = menu;
        this.inventory = Bukkit.createInventory(this, height * MENU_WIDTH, title);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }
    public PaperMenu getMenu() {
        return menu;
    }

    public void invalidate() {
        isValid = false;
    }

    public boolean isValid() {
        return isValid;
    }
}
