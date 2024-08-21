package net.onestorm.library.requirement;

import net.onestorm.library.common.factory.BuildException;
import net.onestorm.library.common.factory.Builder;
import net.onestorm.library.common.factory.context.BuildContext;
import net.onestorm.library.common.factory.context.StorageBuildContext;
import net.onestorm.library.storage.StorageMap;

public abstract class AbstractRequirementBuilder implements Builder<Requirement> {

    @Override
    public Requirement build(BuildContext context) {
        if (!(context instanceof StorageBuildContext storageContext)) {
            throw new BuildException("Context is not an instance of StorageBuildContext.");
        }

        StorageMap storage = storageContext.getStorage();
        boolean isInverted = storage.getBoolean("is-inverted").orElse(false);

        return build(storage, isInverted);
    }

    /**
     * Builds a requirement from configuration section.
     * @param storage The configuration section.
     * @param isReverted Reverts the result from the build requirement when checked.
     * @return The requirement.
     */
    protected abstract Requirement build(StorageMap storage, boolean isReverted);
}
