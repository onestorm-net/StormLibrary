package net.onestorm.library.requirement.implementation;

import net.onestorm.library.configuration.Section;
import net.onestorm.library.requirement.AbstractRequirementBuilder;
import net.onestorm.library.requirement.Requirement;

import java.util.Optional;

public class PermissionRequirementBuilder extends AbstractRequirementBuilder {

    private static final String REQUIREMENT_NAME = "permission";

    @Override
    public String getName() {
        return REQUIREMENT_NAME;
    }

    @Override
    protected Requirement build(Section configuration, boolean isInverted) {
        Optional<String> optional = configuration.getString("permission");

        if (optional.isEmpty()) {
            throw new IllegalArgumentException("Missing \"permission\" key in configuration");
        }

        return new PermissionRequirement(isInverted, optional.get());
    }
}
