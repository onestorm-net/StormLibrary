package net.onestorm.library.paper.menu;


import net.kyori.adventure.text.Component;
import net.onestorm.library.menu.Menu;
import net.onestorm.library.menu.element.Element;
import net.onestorm.library.menu.element.IdentifiableElement;
import net.onestorm.library.menu.slot.Slot;
import net.onestorm.library.paper.menu.element.ClickableElement;
import net.onestorm.library.paper.menu.slot.ItemStackSlot;
import net.onestorm.library.paper.user.PaperOnlineUser;
import net.onestorm.library.user.OnlineUser;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class PaperMenu implements Menu {

    private MenuInventoryHolder inventoryHolder = null;
    private PaperOnlineUser owner = null;
    private Map<String, Object> options;
    private Component currentTitle;
    private Component title;


    // all registered elements
    private final List<Element> elementList = new ArrayList<>();

    // which location is which element
    private final Map<Integer, Element> elementMap = new HashMap<>();

    // which location is which slot
    private final Map<Integer, Slot> slotMap = new HashMap<>();

    @Override
    public void open(OnlineUser user) {
        open(user, new HashMap<>());
    }

    @Override
    public void open(OnlineUser user, Map<String, Object> options) {
        if (!(user instanceof PaperOnlineUser paperUser)) {
            throw new IllegalArgumentException("Cannot open menu, user is not a paper user.");
        }

        this.owner = paperUser;
        this.options = options;

        currentTitle = title;
        inventoryHolder = createNewHolder();

        setContent();

        paperUser.asPlayer().openInventory(inventoryHolder.getInventory());

    }

    @Override
    public void update() {
        if (inventoryHolder == null || !inventoryHolder.isValid()) {
            return; // no need for updating, menu was never opened
        }

        if (title.equals(currentTitle)) {
            setContent();
        } else {
            inventoryHolder.invalidate();
            currentTitle = title;
            inventoryHolder = createNewHolder();
            setContent();
            owner.asPlayer().openInventory(inventoryHolder.getInventory());
        }
    }

    private void setContent() {
        int contentSize = inventoryHolder.getInventory().getSize();
        ItemStack[] content = new ItemStack[contentSize];

        for (Element element : elementList) {

            element.getSlots(this).forEach((index, slot) -> {
                if (index < 0 || index >= contentSize) {
                    return; // continue
                }
                elementMap.put(index, element);
                slotMap.put(index, slot);

                if (!(slot instanceof ItemStackSlot itemStackSlot)) {
                    content[index] = new ItemStack(Material.AIR);
                    return; // continue
                }

                content[index] = itemStackSlot.getItemStack();
            });
        }

        inventoryHolder.getInventory().setContents(content);
        // todo make sure inventory updates are on the minecraft thread
    }

    public void updateSlot(ItemStackSlot slot) {
        Slot currentSlot = slotMap.get(slot.getIndex());
        if (slot != currentSlot) {
            return; // you are not the top slot
            // todo add a way to let a slot know if they are on top, so they dont spam updates when not needed
        }
        inventoryHolder.getInventory().setItem(slot.getIndex(), slot.getItemStack());
        // todo make sure inventory updates are on the minecraft thread
    }

    @Override
    public Map<String, Object> getOptions() {
        return options;
    }

    @Override
    public Component getTitle() {
        return title;
    }

    @Override
    public void setTitle(Component title) {
        this.title = title;
    }

    @Override
    public OnlineUser getOwner() {
        return owner;
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
            if (!(element instanceof IdentifiableElement identifierElement)) {
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
            if (!(element instanceof IdentifiableElement identifierElement)) {
                return false; // continue
            }
            Optional<String> optionalId = identifierElement.getIdentifier();
            return optionalId.isPresent() && optionalId.get().equalsIgnoreCase(id);
        });
    }

    protected abstract MenuInventoryHolder createNewHolder();

    public void handleClick(InventoryClickEvent event) {
        Element element = elementMap.get(event.getSlot());

        if (!(element instanceof ClickableElement clickableElement)) {
            return;
        }

        clickableElement.onClick(this, event.getSlot());
    }

}
