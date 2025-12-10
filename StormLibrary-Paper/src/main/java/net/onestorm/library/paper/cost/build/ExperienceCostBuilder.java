package net.onestorm.library.paper.cost.build;

import net.onestorm.library.common.context.BuildContext;
import net.onestorm.library.common.context.StorageBuildContext;
import net.onestorm.library.common.factory.BuildException;
import net.onestorm.library.common.factory.Builder;
import net.onestorm.library.cost.Cost;
import net.onestorm.library.paper.cost.ExperienceCost;
import net.onestorm.library.paper.cost.ExperienceLevelCost;
import net.onestorm.library.storage.StorageMap;

public class ExperienceCostBuilder implements Builder<Cost> {

    private static final String BUILDER_NAME = "stormlibrary:experience";

    @Override
    public String getName() {
        return "stormlibrary:experience";
    }

    @Override
    public Cost build(BuildContext context) {
        if (!(context instanceof StorageBuildContext storageContext)) {
            throw new BuildException("BuildContext is not an instance of StorageBuildContext.");
        }

        StorageMap storage = storageContext.getStorage();
        int amount = storage.getInteger("amount")
            .orElseThrow(() -> new BuildException("Missing \"amount\" key in storage while building: " + BUILDER_NAME));
        boolean useLevels = storage.getBoolean("use-levels").orElse(false);

        if (useLevels) {
            return new ExperienceLevelCost(amount);
        } else {
            return new ExperienceCost(amount);
        }
    }
}
