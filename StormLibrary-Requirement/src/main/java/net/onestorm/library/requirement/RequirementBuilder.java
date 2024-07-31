package net.onestorm.library.requirement;

import net.onestorm.library.storage.StorageMap;

public interface RequirementBuilder {

    /**
     * Gets the name of the requirement builder.
     * <p>
     * The name should be unique when registering.
     * @return The name.
     */
    String getName();

    /**
     * Builds a requirement from configuration section.
     * @param storage The storage map section.
     * @return The requirement.
     */
    Requirement build(StorageMap storage);


}
