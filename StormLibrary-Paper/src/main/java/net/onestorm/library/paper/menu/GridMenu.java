package net.onestorm.library.paper.menu;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.onestorm.library.menu.Menu;
import net.onestorm.library.menu.element.Element;
import net.onestorm.library.paper.menu.element.PaperElement;
import net.onestorm.library.paper.user.PaperUser;
import net.onestorm.library.user.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class GridMenu implements InventoryHolder, Menu {

    private final List<Element> elementList = new CopyOnWriteArrayList<>();
    private Inventory inventory = null;

    protected Player player;

    abstract Component getTitle();

    abstract int getWidth();

    abstract int getHeight();

    public GridMenu(Player player) {
        this.player = player;
    }


    public void addElement(Element element) {
        elementList.add(element);
    }

    @Override
    public void open(User user) {
        if (!(user instanceof PaperUser paperUser)) {
            user.sendMessage(Component.text("Error: This menu implementation only works for Paper players.", NamedTextColor.RED));
            return;
        }
        open(paperUser);
    }

    public void open(PaperUser user) {
        user.getPlayer().openInventory(getInventory());
    }

    @Override
    public @NotNull Inventory getInventory() {
        if (inventory != null) {
            return inventory;
        }

        Map<Integer, ItemStack> finalContent = new HashMap<>();

        for (Element element : elementList) {
            if (!(element instanceof PaperElement paperElement)) {
                continue;
            }

            Map<Integer, ItemStack> content = new HashMap<>();
            paperElement.setItems(player, content);
            finalContent.putAll(content);
        }

        int width = getWidth();
        int height = getHeight();

        if (width == 9) {
            if (height > 6 || height < 1) {
                throw new IllegalArgumentException("Unsupported menu height for the width 9, should be 1-6");
            }
            inventory = Bukkit.createInventory(this, width * height, getTitle());
        }
        else if (width == 3) {
            // just ignore height
            inventory = Bukkit.createInventory(this, InventoryType.DISPENSER, getTitle());
        }
        else {
            throw new IllegalArgumentException("Unsupported menu width, should be 3 or 9");
        }

        finalContent.forEach((slot, item) -> {
            inventory.setItem(slot, item);
        });

        return inventory;

    }
}
