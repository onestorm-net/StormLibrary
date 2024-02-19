package net.onestorm.library.paper.menu.element;

import net.onestorm.library.menu.element.Element;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public interface PaperElement extends Element {

    void setItems(Player player, Map<Integer, ItemStack> content);

}
