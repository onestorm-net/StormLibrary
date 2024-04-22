package net.onestorm.library.paper.menu;

import net.kyori.adventure.text.Component;
import net.onestorm.library.menu.element.Element;
import net.onestorm.library.menu.element.ElementMenu;
import net.onestorm.library.menu.element.Identifiable;
import net.onestorm.library.user.OnlineUser;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PaperElementMenu implements ElementMenu, InventoryHolder {

    private Inventory inventory = null;
    private final List<Element> elementList = new ArrayList<>(); // all registered elements
    private final Map<Integer, Element> elementMap = new HashMap<>(); // which location is which element

    @Override
    public void open(OnlineUser user) {

    }

    @Override
    public List<Element> getElements() {
        return elementList;
    }

    @Override
    public List<Element> getElements(String name) {
        List<Element> result = new ArrayList<>();

        for (Element element : elementList) {
            if (element.getName().equalsIgnoreCase(name)) {
                result.add(element);
            }
        }

        return result;
    }

    @Override
    public Optional<Element> getElement(String id) {
        Element result = null;

        for (Element element : elementList) {
            if (!(element instanceof Identifiable identifierElement)) {
                continue;
            }

            Optional<String> optionalId = identifierElement.getIdentifier();

            if (optionalId.isEmpty() || !optionalId.get().equalsIgnoreCase(id)) {
                continue;
            }

            result = element;
            break; // id should be unique, just return the first element we find
        }

        return Optional.ofNullable(result);
    }

    @Override
    public void addElement(Element element) {
        elementList.add(element);
    }

    @Override
    public void removeElement(Element element) {
        elementList.remove(element);
    }

    @Override
    public void removeElement(String id) {
        elementList.removeIf(element -> {
            if (!(element instanceof Identifiable identifierElement)) {
                return false; // continue
            }
            Optional<String> optionalId = identifierElement.getIdentifier();
            return optionalId.isPresent() && optionalId.get().equalsIgnoreCase(id);
        });
    }

    @Override
    public @NotNull Inventory getInventory() {
        if (inventory != null) {
            return inventory;
        }

        int size = 54;
        inventory = Bukkit.createInventory(this, size, Component.text("TEST MENU"));

        for (Element element : elementList) {

            for (int slotIndex : element.getSlotIndexes()) {
                if (slotIndex < 0 || slotIndex > size) {
                    continue;
                }

                elementMap.put(slotIndex, element);
            }
        }

        for (Map.Entry<Integer, Element> elementEntry : elementMap.entrySet()) {
            int slotIndex = elementEntry.getKey();
            Element element = elementEntry.getValue();

            if (!(element.getSlot(slotIndex) instanceof PaperSlot slot)) {
                inventory.addItem(new ItemStack(Material.AIR));
                continue;
            }

            inventory.addItem(slot.getItemStack());
        }

        return inventory;
    }
}
