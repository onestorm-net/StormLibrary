package net.onestorm.library.paper.menu.element.build;

import net.onestorm.library.common.factory.BuildException;
import net.onestorm.library.common.factory.Builder;
import net.onestorm.library.menu.element.Element;
import net.onestorm.library.paper.menu.element.ItemStackElement;
import net.onestorm.library.paper.util.ItemStackUtil;
import net.onestorm.library.storage.StorageMap;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ItemStackElementBuilder implements Builder<Element> {

    private static final String BUILDER_NAME = "stormlibrary:item-stack";
    private static final int DEFAULT_WIDTH = 1;
    private static final int DEFAULT_HEIGHT = 1;
    @Override
    public String getName() {
        return BUILDER_NAME;
    }

    @Override
    public Element build(StorageMap storage) {
        StorageMap itemMap = storage.getMap("item-stack").orElseThrow(() -> new BuildException("Missing \"item-stack\" key in storage while building: " + BUILDER_NAME));
        int x = storage.getInteger("x").orElseThrow(() -> new BuildException("Missing \"x\" key in storage while building: " + BUILDER_NAME));
        int y = storage.getInteger("y").orElseThrow(() -> new BuildException("Missing \"y\" key in storage while building: " + BUILDER_NAME));
        int width = storage.getInteger("width").orElse(DEFAULT_WIDTH);
        int height = storage.getInteger("height").orElse(DEFAULT_HEIGHT);

        List<Integer> slots = new ArrayList<>();
        for (int indexY = y; indexY < y + height; indexY++) {
            for (int indexX = x; indexX < x + width; indexX++) {
                slots.add(indexY * 9 + indexX);
            }
        }

        ItemStack itemStack = ItemStackUtil.fromStorage(itemMap);
        return new ItemStackElement(slots, itemStack);
    }
}
