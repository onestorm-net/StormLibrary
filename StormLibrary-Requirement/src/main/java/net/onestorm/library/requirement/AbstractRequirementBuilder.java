package net.onestorm.library.requirement;

import net.onestorm.library.common.factory.Builder;
import net.onestorm.library.storage.StorageMap;

public abstract class AbstractRequirementBuilder implements Builder<Requirement> {

    @Override
    public Requirement build(StorageMap storage) {

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
