package net.onestorm.library.paper.menu.element.build;

import net.onestorm.library.common.factory.BuildException;
import net.onestorm.library.menu.Menu;
import net.onestorm.library.menu.element.build.AbstractElementBuilder;
import net.onestorm.library.menu.element.Element;
import net.onestorm.library.paper.menu.element.ItemStackElement;
import net.onestorm.library.paper.util.ItemStackUtil;
import net.onestorm.library.storage.StorageMap;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemStackElementBuilder extends AbstractElementBuilder {

    private static final String BUILDER_NAME = "stormlibrary:item-stack";

    @Override
    public String getName() {
        return BUILDER_NAME;
    }

    @Override
    protected Element createElement(StorageMap storage, Menu menu, List<Integer> slots) {
        StorageMap itemMap = storage.getMap("item-stack")
                .orElseThrow(() -> new BuildException("Missing \"item-stack\" key in storage while building: " + BUILDER_NAME));
        ItemStack itemStack = ItemStackUtil.fromStorage(itemMap);
        return new ItemStackElement(slots, itemStack);
    }
}
