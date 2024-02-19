package net.onestorm.library.requirement;

import net.onestorm.library.configuration.Section;

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
     * @param configuration The configuration section.
     * @return The requirement.
     */
    Requirement build(Section configuration);


}
