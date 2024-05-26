package net.onestorm.library.requirement;

import net.onestorm.library.storage.StorageMap;

public abstract class AbstractRequirementBuilder implements RequirementBuilder {

    @Override
    public Requirement build(StorageMap storage) {

        boolean isInverted = storage.getBoolean("is-inverted").orElse(false);

        return build(storage, isInverted);
    }

    /**
     * Builds a requirement from configuration section.
     * @param configuration The configuration section.
     * @param isReverted Reverts the result from the build requirement when checked.
     * @return The requirement.
     */
    protected abstract Requirement build(StorageMap storage, boolean isReverted);
}
