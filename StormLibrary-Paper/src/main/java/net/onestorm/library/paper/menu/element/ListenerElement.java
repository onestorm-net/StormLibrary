package net.onestorm.library.paper.menu.element;

import net.onestorm.library.paper.menu.PaperMenu;
import org.bukkit.event.inventory.InventoryClickEvent;

public interface ListenerElement {

    void onClick(InventoryClickEvent event, PaperMenu menu);

    void onInventoryClick(InventoryClickEvent event, PaperMenu menu);

}
