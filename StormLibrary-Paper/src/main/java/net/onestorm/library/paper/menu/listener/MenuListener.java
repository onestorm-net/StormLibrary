package net.onestorm.library.paper.menu.listener;

import net.onestorm.library.paper.menu.MenuHolder;
import net.onestorm.library.paper.menu.PaperMenu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.java.JavaPlugin;

public class MenuListener implements Listener {

    public MenuListener(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        InventoryHolder inventoryHolder = inventory.getHolder();

        if (!(inventoryHolder instanceof MenuHolder menuHolder)) {
            return;
        }

        event.setCancelled(true);

        if (menuHolder.isInvalidated()) {
            menuHolder.getMenu().close();
            return;
        }

        PaperMenu menu = menuHolder.getMenu();
        menu.onClick(event);
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        Inventory inventory = event.getInventory();
        InventoryHolder holder = inventory.getHolder();

        if (!(holder instanceof MenuHolder menuHolder)) {
            return;
        }

        event.setCancelled(true);

        if (menuHolder.isInvalidated()) {
            menuHolder.getMenu().close();
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        InventoryHolder inventoryHolder = event.getInventory().getHolder();

        if (!(inventoryHolder instanceof MenuHolder menuHolder)) {
            return;
        }

        if (!menuHolder.isInvalidated()) {
            menuHolder.invalidate();
        }

        PaperMenu menu = menuHolder.getMenu();

        if (event.getReason() != InventoryCloseEvent.Reason.OPEN_NEW) {
            menu.onClose();
            return;
        }

        MenuHolder newHolder = menu.getMenuHolder();

        // menu holders are still the same on close, this indicates this close is not from a (full) menu update
        if (menuHolder == newHolder) {
            menu.onClose();
        }
    }
}
