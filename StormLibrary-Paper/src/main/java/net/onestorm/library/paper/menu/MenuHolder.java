package net.onestorm.library.paper.menu;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public class MenuHolder implements InventoryHolder {

    private final PaperMenu menu;
    private final Inventory inventory;
    private final Component title;

    private boolean isInvalidated = false;

    public MenuHolder(PaperMenu menu, InventoryType type) {
        this.menu = menu;
        this.inventory = Bukkit.createInventory(this, type);
        this.title = type.defaultTitle();
    }

    public MenuHolder(PaperMenu menu, InventoryType type, Component title) {
        this.menu = menu;
        this.inventory = Bukkit.createInventory(this, type, title);
        this.title = title;
    }

    public MenuHolder(PaperMenu menu, int size) {
        this.menu = menu;
        this.inventory = Bukkit.createInventory(this, size);
        this.title = InventoryType.CHEST.defaultTitle();
    }

    public MenuHolder(PaperMenu menu, int size, Component title) {
        this.menu = menu;
        this.inventory = Bukkit.createInventory(this, size, title);
        this.title = title;
    }

    public Component getTitle() {
        return title;
    }

    public @NotNull Inventory getInventory() {
        return inventory;
    }

    public PaperMenu getMenu() {
        return menu;
    }

    public boolean isInvalidated() {
        return isInvalidated;
    }

    public void invalidate() {
        isInvalidated = true;
        inventory.clear();
    }

}
