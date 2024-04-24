package net.onestorm.library.paper.menu;

import net.kyori.adventure.text.Component;
import net.onestorm.library.menu.ClickableMenu;
import net.onestorm.library.menu.MenuClickEvent;
import net.onestorm.library.menu.element.ClickableElement;
import net.onestorm.library.menu.element.Element;
import net.onestorm.library.menu.ElementMenu;
import net.onestorm.library.menu.element.IdentifiableElement;
import net.onestorm.library.paper.user.PaperOnlineUser;
import net.onestorm.library.user.OnlineUser;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PaperElementMenu implements ElementMenu<ItemStack>, ClickableMenu<ItemStack> {

    private static final int MENU_WIDTH = 9;

    private PaperOnlineUser owner = null;
    private MenuInventoryHolder inventoryHolder;
    private Component title;
    private final int height;
    private Component newTitle;
    private final List<Element<ItemStack>> elementList = new ArrayList<>(); // all registered elements
    private final Map<Integer, Element<ItemStack>> elementMap = new HashMap<>(); // which location is which element

    public PaperElementMenu(Component title, int height) {
        if (height > 6) {
            this.height = 6;
        } else if (height < 1) {
            this.height = 1;
        } else {
            this.height = height;
        }
        this.title = title;
        this.newTitle = title;
    }

    @Override
    public void open(OnlineUser user) {
        if (!(user instanceof PaperOnlineUser paperUser)) {
            throw new IllegalArgumentException("Not a paper user");
        }
        this.owner = paperUser;

        inventoryHolder = new MenuInventoryHolder(height, title, this);

        setContent();

        paperUser.asPlayer().openInventory(inventoryHolder.getInventory());
    }

    @Override
    public Component getTitle() {
        return title;
    }

    @Override
    public void setTitle(Component title) {
        this.newTitle = title;
    }

    @Override
    public void update() {
        if (inventoryHolder == null || !inventoryHolder.isValid()) {
            return; // no need for updating, menu was never opened
        }

        if (title.equals(newTitle)) {
            setContent();
        } else {
            inventoryHolder.invalidate();
            title = newTitle;
            inventoryHolder = new MenuInventoryHolder(height, title, this);
            setContent();
            owner.asPlayer().openInventory(inventoryHolder.getInventory());
        }
    }

    private void setContent() {

        ItemStack[] content = new ItemStack[height * MENU_WIDTH];

        for (Element<ItemStack> element : elementList) {

            element.getItems(owner).forEach((slot, item) -> {
                if (slot < 0 || slot > height * MENU_WIDTH) {
                    return; // continue
                }
                elementMap.put(slot, element);
                content[slot] = item;
            });
        }

        inventoryHolder.getInventory().setContents(content);
    }

    @Override
    public List<Element<ItemStack>> getElements() {
        return elementList;
    }

    @Override
    public List<Element<ItemStack>> getElements(String name) {
        List<Element<ItemStack>> result = new ArrayList<>();

        for (Element<ItemStack> element : elementList) {
            if (element.getName().equalsIgnoreCase(name)) {
                result.add(element);
            }
        }

        return result;
    }

    @Override
    public Optional<Element<ItemStack>> getElement(String id) {
        Element<ItemStack> result = null;

        for (Element<ItemStack> element : elementList) {
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
    public void addElement(Element<ItemStack> element) {
        elementList.add(element);
    }

    @Override
    public void removeElement(Element<ItemStack> element) {
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

    @Override
    public void onMenuClick(MenuClickEvent<ItemStack> event) {
        Element<ItemStack> element = elementMap.get(event.getSlot());

        if (!(element instanceof ClickableElement<ItemStack> clickableElement)) {
            return;
        }

        clickableElement.onElementClick(event);
    }
}
