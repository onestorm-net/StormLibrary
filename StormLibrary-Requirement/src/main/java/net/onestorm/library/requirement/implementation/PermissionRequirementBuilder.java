package net.onestorm.library.requirement.implementation;

import net.onestorm.library.requirement.AbstractRequirementBuilder;
import net.onestorm.library.requirement.Requirement;
import net.onestorm.library.storage.StorageMap;

import java.util.Optional;

public class PermissionRequirementBuilder extends AbstractRequirementBuilder {

    private static final String REQUIREMENT_NAME = "permission";

    @Override
    public String getName() {
        return REQUIREMENT_NAME;
    }

    @Override
    protected Requirement build(StorageMap storage, boolean isInverted) {
        Optional<String> optional = storage.getString("permission");

        if (optional.isEmpty()) {
            throw new IllegalArgumentException("Missing \"permission\" key in configuration");
        }

        return new PermissionRequirement(isInverted, optional.get());
    }
}
