package net.onestorm.library.requirement;

import net.onestorm.library.configuration.Section;

public abstract class AbstractRequirementBuilder implements RequirementBuilder {

    @Override
    public Requirement build(Section configuration) {

        boolean isInverted = configuration.getBoolean("is-inverted").orElse(false);

        return build(configuration, isInverted);
    }

    /**
     * Builds a requirement from configuration section.
     * @param configuration The configuration section.
     * @param isReverted Reverts the result from the build requirement when checked.
     * @return The requirement.
     */
    protected abstract Requirement build(Section configuration, boolean isReverted);
}
