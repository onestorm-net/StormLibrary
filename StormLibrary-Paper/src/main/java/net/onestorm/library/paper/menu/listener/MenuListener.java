package net.onestorm.library.paper.menu.listener;

import net.onestorm.library.menu.ClickableMenu;
import net.onestorm.library.menu.Menu;
import net.onestorm.library.paper.menu.PaperMenuClickEvent;
import net.onestorm.library.paper.menu.MenuInventoryHolder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class MenuListener implements Listener {

    private final JavaPlugin plugin;

    public MenuListener(JavaPlugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        InventoryHolder holder = inventory.getHolder();

        if (!(holder instanceof MenuInventoryHolder menuInventoryHolder)) {
            return;
        }

        event.setCancelled(true);

        if (!menuInventoryHolder.isValid()) {
            plugin.getServer().getScheduler().runTask(plugin, inventory::close);
            return;
        }

        Menu menu = menuInventoryHolder.getMenu();

        if (!(menu instanceof ClickableMenu)) {
            return;
        }

        @SuppressWarnings("unchecked") // todo not sure if this is a safe way.
        ClickableMenu<ItemStack> clickableMenu = (ClickableMenu<ItemStack>) menu;
        clickableMenu.onMenuClick(new PaperMenuClickEvent(event, menu));
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        Inventory inventory = event.getInventory();
        InventoryHolder holder = inventory.getHolder();

        if (!(holder instanceof MenuInventoryHolder menuInventoryHolder)) {
            return;
        }

        event.setCancelled(true);

        if (!menuInventoryHolder.isValid()) {
            plugin.getServer().getScheduler().runTask(plugin, inventory::close);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        onInventoryClose(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        onInventoryClose(event.getPlayer());
    }

    @EventHandler
    public void onPlayerChangeWorld(PlayerChangedWorldEvent event) {
        onInventoryClose(event.getPlayer());
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        onInventoryClose(event.getPlayer());
    }

    private void onInventoryClose(Player player) {
        Inventory inventory = player.getOpenInventory().getTopInventory();

        InventoryHolder holder = inventory.getHolder();

        if (!(holder instanceof MenuInventoryHolder menuInventoryHolder)) {
            return;
        }

        if (!menuInventoryHolder.isValid()) {
            return;
        }

        menuInventoryHolder.invalidate();
    }
}
