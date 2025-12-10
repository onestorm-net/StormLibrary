package net.onestorm.library.paper.cost.build;

import net.onestorm.library.common.context.BuildContext;
import net.onestorm.library.common.context.StorageBuildContext;
import net.onestorm.library.common.factory.BuildException;
import net.onestorm.library.common.factory.Builder;
import net.onestorm.library.cost.Cost;
import net.onestorm.library.paper.cost.ItemStackCost;
import net.onestorm.library.paper.util.ItemStackUtil;
import net.onestorm.library.storage.StorageMap;
import org.bukkit.inventory.ItemStack;

public class ItemStackCostBuilder implements Builder<Cost> {

    private static final String BUILDER_NAME = "stormlibrary:item-stack";

    @Override
    public String getName() {
        return BUILDER_NAME;
    }

    @Override
    public Cost build(BuildContext context) {
        if (!(context instanceof StorageBuildContext storageContext)) {
            throw new BuildException("BuildContext is not an instance of StorageBuildContext.");
        }

        StorageMap storage = storageContext.getStorage();
        int amount = storage.getInteger("amount")
            .orElseThrow(() -> new BuildException("Missing \"amount\" key in storage while building: " + BUILDER_NAME));
        StorageMap itemMap = storage.getMap("item-stack")
            .orElseThrow(() -> new BuildException("Missing \"item-stack\" key in storage while building: " + BUILDER_NAME));
        ItemStack itemStack = ItemStackUtil.fromStorage(itemMap);
        return new ItemStackCost(itemStack, amount);
    }
}
