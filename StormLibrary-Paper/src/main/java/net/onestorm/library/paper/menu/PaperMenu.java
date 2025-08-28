package net.onestorm.library.paper.menu;


import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.onestorm.library.common.context.UserContext;
import net.onestorm.library.menu.AbstractMenu;
import net.onestorm.library.menu.element.CloseableElement;
import net.onestorm.library.menu.element.Element;
import net.onestorm.library.menu.element.ElementComparator;
import net.onestorm.library.menu.cell.Cell;
import net.onestorm.library.paper.context.PlayerContext;
import net.onestorm.library.paper.menu.element.ListenerElement;
import net.onestorm.library.paper.menu.cell.ItemStackCell;
import net.onestorm.library.paper.user.PaperUser;
import net.onestorm.library.user.User;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class PaperMenu extends AbstractMenu {

    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();
    private static final Comparator<Element> ELEMENT_COMPARATOR = new ElementComparator();

    private MenuHolder menuHolder;
    private PlayerContext context;

    private final Map<Integer, Element> elementMap = new HashMap<>();
    private final Map<Integer, Cell> cellMap = new HashMap<>();

    @Override
    public void open(UserContext context) {
        if (menuHolder != null) {
            throw new IllegalStateException("Menu was already opened!");
        }

        if (!(context instanceof PlayerContext playerContext)) {
            throw new IllegalArgumentException("Cannot open menu, this menu needs a PlayerContext (Paper) to open");
        }

        this.context = playerContext;
        menuHolder = createMenuHolder();
        setContent();
        playerContext.getPlayer().openInventory(menuHolder.getInventory());
    }

    @Override
    public void update() {
        if (menuHolder == null || menuHolder.isInvalidated()) {
            return; // no need for updating, menu was never opened
        }

        Component title = createTitle();

        if (title != null && !title.equals(currentTitle)) {
            menuHolder.invalidate();
            menuHolder = createMenuHolder();
            currentTitle = title;
            setContent();
            owner.asPlayer().openInventory(menuHolder.getInventory());
        } else {
            setContent();
        }
    }

    private void setContent() {
        int contentSize = getSize();
        ItemStack[] content = new ItemStack[contentSize];

        List<Element> elementList = getElements();
        elementList.sort(ELEMENT_COMPARATOR);

        for (Element element : elementList) {

            element.getContent(this).forEach(cell -> {
                int index = cell.getIndex();
                if (index < 0 || index >= contentSize) {
                    return; // continue
                }
                elementMap.put(index, element);
                cellMap.put(index, cell);

                if (!(cell instanceof ItemStackCell itemStackCell)) {
                    content[index] = new ItemStack(Material.AIR);
                    return; // continue
                }

                content[index] = itemStackCell.getItemStack();
            });
        }

        menuHolder.getInventory().setContents(content);
        // todo make sure inventory updates are on the minecraft thread
    }

    @Override
    public void updateCell(Cell cell) {
        if (menuHolder == null || menuHolder.isInvalidated()) {
            return; // nothing to update
        }
        int index = cell.getIndex();
        Cell currentCell = cellMap.get(cell.getIndex());

        if (cell != currentCell) {
            return; // cell is not the top slot
        }

        if (!(cell instanceof ItemStackCell itemStackCell)) {
            return;
        }

        menuHolder.getInventory().setItem(index, itemStackCell.getItemStack());
        // todo make sure inventory updates are on the minecraft thread
    }

    @Override
    public Map<String, Object> getOptions() {
        return Collections.emptyMap();
    }

    @Override
    public User getOwner() {
        return context.getUser();
    }

    @Override
    public void close() {
        if (menuHolder == null) {
            return;
        }
        menuHolder.getInventory().close();
    }

    public Component createTitle() {
        return MINI_MESSAGE.deserialize(getTitle(), TagResolver.resolver(tagResolvers));
    }

    protected abstract MenuHolder createMenuHolder();

    @ApiStatus.Internal
    public MenuHolder getMenuHolder() {
        return menuHolder;
    }

    // events

    public void onClick(InventoryClickEvent event) {
        for (Element element : getElements()) {
            if (element instanceof ListenerElement listenerElement) {
                listenerElement.onInventoryClick(event, this); // inventory click
            }
        }
        if (event.getClickedInventory() != event.getView().getTopInventory()) {
            return;
        }
        Element element = elementMap.get(event.getSlot());

        if (element instanceof ListenerElement listenerElement) {
            listenerElement.onClick(event, this); // element click
        }
    }

    public void onClose() {
        for (Element element : getElements()) {
            if (element instanceof CloseableElement closeableElement) {
                closeableElement.close(this);
            }
        }
    }
}
